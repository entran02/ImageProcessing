# python generatemask.py <mask-img-name.ppm> <width> <height>

import sys

with open(sys.argv[1], "w") as f:
    f.write(f"P3 \n{sys.argv[2]} {sys.argv[3]} 255 \n")
    for i in range(int(sys.argv[3])):
        for j in range(int(sys.argv[2])):
            if i < int(sys.argv[3]) // 2 and j < int(sys.argv[2]) // 2:
                f.write("0 0 0 \n")
            else:
                f.write("255 255 255 \n")
