import cv2
import numpy as np
from math import sqrt, pi, dist, asin, sin, cos

MIN_RADIUS = 10
MAX_RADIUS = 1000


def overlapping_area(circle_a, circle_b):
    ax, ay, ar = circle_a
    bx, by, br = circle_b

    d = dist((ax, ay), (bx, by))
    a = ar ** 2
    b = br ** 2

    if d <= abs(br - ar):
        return pi * min(a, b)
    elif d < ar + br:
        x = (a - b + d * d) / (2 * d)
        z = x * x
        y = sqrt(a - z)

        one = max(z + b - a, 0)
        two = min(max(y / ar, -1), 1)
        three = min(max(y / br, -1), 1)

        return a * asin(two) + b * asin(three) - y * (x + one)
    else:
        return 0


def create_mask(image, red=False):
    if red:
        blur = (17, 17)
        lower0 = np.array([0, 70, 70])
        upper0 = np.array([10, 255, 255])
        lower1 = np.array([160, 40, 40])
        upper1 = np.array([190, 255, 255])
    else:
        blur = (33, 33)
        lower0 = np.array([81, 101, 94])
        upper0 = np.array([95, 255, 138])
        lower1 = np.array([88, 127, 44])
        upper1 = np.array([109, 255, 255])

    blurred = cv2.GaussianBlur(image, blur, cv2.BORDER_DEFAULT)
    image_hsv = cv2.cvtColor(blurred, cv2.COLOR_BGR2HSV)
    mask0 = cv2.inRange(image_hsv, lower0, upper0)
    mask1 = cv2.inRange(image_hsv, lower1, upper1)
    mask = mask0 + mask1

    cv2.imshow("{} - {}".format(red, 0), mask0)
    cv2.imshow("{} - {}".format(red, 1), mask1)
    cv2.imshow("{} - All".format(red), mask)

    return mask


def find_features(mask, min_area, max_area):
    # https://stackoverflow.com/questions/9860667/writing-robust-color-and-size-invariant-circle-detection-with-opencv-based-on
    output = list()
    try:
        detector = cv2.MSER_create()
        detector.setMinArea(int(min_area))
        detector.setMaxArea(int(max_area))
        tmp = detector.detect(cv2.GaussianBlur(mask, (3, 3), cv2.BORDER_DEFAULT))
        fs = list(tmp)
        fs.sort(key=lambda x: -x.size)

        def supress(x):
            for f in fs:
                distx = f.pt[0] - x.pt[0]
                disty = f.pt[1] - x.pt[1]
                dist = sqrt(distx * distx + disty * disty)
                if (f.size > x.size) and (dist < f.size / 2):
                    return True

        sfs = [x for x in fs if not supress(x)]

        for f in sfs:
            output.append(((int(f.pt[0]), int(f.pt[1])), int(f.size / 2), 1/len(sfs)))
    except Exception as e:
        print(e)
    return output


def find_contour_circles(mask):
    output = []
    contours = cv2.findContours(mask, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    contours = contours[0] if len(contours) == 2 else contours[1]
    for c in contours:
        center, radius = cv2.minEnclosingCircle(c)
        fill = cv2.contourArea(c)/(pi * radius**2)
        if fill > .0:
            output.append(((int(center[0]), int(center[1])), int(radius), fill))
    output.sort(key=lambda x: -x[2])
    return output


def find_circle(mask):
    contours = find_contour_circles(mask)
    if len(contours) > 0:
        center, radius, confidence = contours[0]
        return center[0], center[1], radius
    else:
        return None, None, None


def old_find_circle(mask):
    features = find_features(mask, min_area=50, max_area=70000)
    contours = find_contour_circles(mask)

    # copy = cv2.cvtColor(mask, cv2.COLOR_GRAY2BGR)
    # for center, radius, _ in contours:
    #     cv2.circle(copy, center, radius, (0, 255, 0), 2)
    # cv2.imshow("contours", copy)
    #
    # copy = cv2.cvtColor(mask, cv2.COLOR_GRAY2BGR)
    # for center, radius, _ in features:
    #     cv2.circle(copy, center, radius, (0, 255, 0), 2)
    # cv2.imshow("features", copy)

    fits = list()
    for fcenter, fradius, fconfidence in features:
        bratio = -1
        bcenter, bradius = None, None
        for ccenter, cradius, cconfidence in contours:
            overlap = overlapping_area((fcenter[0], fcenter[1], fradius), (ccenter[0], ccenter[1], cradius))
            overlap_ratio = (overlap / max(fradius**2 * pi, cradius**2 * pi)) * fconfidence * cconfidence
            if overlap_ratio > bratio:
                bratio = overlap_ratio
                bcenter, bradius = ccenter, cradius
        if bratio > 0:
            fits.append((bcenter, bradius, bratio))

    fits.sort(key=lambda x: -x[2])

    if len(fits) > 0:
        best = fits[0]
        return best[0][0], best[0][1], best[1]
    elif len(contours) > 0:
        ccenter, cradius, _ = contours[0]
        return ccenter[0], ccenter[1], cradius
    elif len(features) > 0:
        fcenter, fradius, _ = features[0]
        return fcenter[0], fcenter[1], fradius
    else:
        return None, None, None


# runPipeline() is called every frame by Limelight's backend.
def runPipeline(image, llrobot, red=True):
    mask = create_mask(image, red=red)
    x, y, r = find_circle(mask)

    if x is not None and y is not None and r is not None:
        num_points = 50
        circle_contour = np.zeros((num_points, 1, 2))

        # Plot the points of a circle
        # A dumb way of looping through 0% - 100%
        for i in range(0, num_points):
            rads = 2 * pi * (1 / num_points) * i
            cx = r * cos(rads) + x
            cy = r * sin(rads) + y
            circle_contour[i, 0, :] = np.asarray([cx, cy])

        return circle_contour.astype(int), image, llrobot
    else:
        return [], image, llrobot


if __name__ == '__main__':
    vc = cv2.VideoCapture(0)
    while True:
        retval, image = vc.read()
        if retval:
            rcontour, _, _ = runPipeline(image, None, False)
            if rcontour is not None and len(rcontour) > 0:
                cv2.drawContours(image, [rcontour], -1, (255, 0, 0), 5)
            bcontour, _, _ = runPipeline(image, None, True)
            if bcontour is not None and len(bcontour) > 0:
                cv2.drawContours(image, [bcontour], -1, (0, 0, 255), 5)
            cv2.imshow("Image", image)
            cv2.waitKey(1)
        else:
            print("No image")
                        