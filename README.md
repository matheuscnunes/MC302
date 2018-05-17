# MC302
Student app repository

# Esse código faz magia

BufferedImage bufferedImage = new BufferedImage(
                120, 15,
                BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.drawString("G O S T O S O S", 12, 12);

        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                stringBuilder.append(bufferedImage.getRGB(x, y) == -16777216 ? "*" : " ");
            }

            if (stringBuilder.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(stringBuilder);
        }
