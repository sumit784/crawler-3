function adjustImageWidth(target, width) {
    var originalWidth = target.width;
    var originalHeight = target.height;
    if (originalWidth > width) {
        target.height = originalHeight * width / originalWidth;
        target.width = width;
    }
}