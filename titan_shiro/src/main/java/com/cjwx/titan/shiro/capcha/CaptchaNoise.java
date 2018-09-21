package com.cjwx.titan.shiro.capcha;

import com.google.code.kaptcha.NoiseProducer;
import com.google.code.kaptcha.util.Configurable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Description: 验证码噪点
 * @Author: qian li
 * @Date: 2018年09月21日  9:45
 */
public class CaptchaNoise extends Configurable implements NoiseProducer {

    @Override
    public void makeNoise(BufferedImage image, float factorOne, float factorTwo, float factorThree, float factorFour) {
        Color color = this.getConfig().getNoiseColor();
        int width = image.getWidth();
        int height = image.getHeight();

        Point2D[] pts;
        Random rand = new Random();
        // 三次贝塞尔样条曲线
        CubicCurve2D cc = new CubicCurve2D.Float(width * factorOne, height * rand.nextFloat(),//起始点坐标
                width * factorTwo, height * rand.nextFloat(),//第一个控制点坐标
                width * factorThree, height * rand.nextFloat(),//第二个控制点坐标
                width * factorFour, height * rand.nextFloat());//结束点坐标
        PathIterator pi = cc.getPathIterator(null, 2);
        Point2D[] tmp = new Point2D[200];
        int i = 0;
        while (!pi.isDone()) {
            float[] coords = new float[6];
            switch (pi.currentSegment(coords)) {
                case PathIterator.SEG_MOVETO: //SEG_MOVETO 和 SEG_LINETO 类型返回一个点
                case PathIterator.SEG_LINETO:
                case PathIterator.SEG_CUBICTO: //SEG_CUBICTO 返回 3 个点
                case PathIterator.SEG_QUADTO: //SEG_QUADTO 返回两个点
                    tmp[i] = new Point2D.Float(coords[0], coords[1]);
                default:
                    ++i;
                    pi.next();
            }
        }
        pts = new Point2D[i];
        System.arraycopy(tmp, 0, pts, 0, i);

        Graphics2D graph = (Graphics2D) image.getGraphics();
        graph.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        graph.setColor(color);

        // 添加干扰线
        for (i = 0; i < pts.length - 1; ++i) {
            if (i < 3) {
                graph.setStroke(new BasicStroke(0.9F * (4 - i)));
            }
            graph.drawLine((int) pts[i].getX(), (int) pts[i].getY(), (int) pts[i + 1].getX(), (int) pts[i + 1].getY());
        }

        // 添加噪点
        int s = rand.nextInt(20);
        for (i = 0; i < s; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            int w = 1 + rand.nextInt(2);
            int h = 1 + rand.nextInt(2);
            graph.drawOval(x, y, w, h);
        }
        graph.dispose();
    }

}
