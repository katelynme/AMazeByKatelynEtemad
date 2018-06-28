package katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.falstad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Handles maze graphics.
 */
public class MazePanel extends View {
    Canvas canvas;
    Paint paint;
    Bitmap bitmap;

    /**
     * Constructor with one context parameter.
     * @param context
     */
    public MazePanel(Context context) {
        super(context);
        bitmap = Bitmap.createBitmap(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
    }
    /**
     * Constructor with two parameters: context and attributes.
     * @param context
     * @param app
     */
    public MazePanel(Context context, AttributeSet app) {
        super(context, app);
    }
    /**
     * Draws given canvas.
     * @param c
     */
    @Override
    public void onDraw(Canvas c) {
        // TODO: draw bitmap
    }

    /**
     * Measures the view and its content to determine the measured width and the measured height.
     * @param width
     * @param height
     */
    @Override
    public void onMeasure(int width, int height) {
        // as described for superclass method
    }

    /**
     * Updates maze graphics.
     */
    public void update() {
        //TODO: update maze graphics
    }

    /**
     * Takes in color string, sets paint color to corresponding color.
     * @param c string
     */
    public void setColor(String c) {
        // TODO: same as setColor(int) but for string parameters
    }

    /**
     * Sets paint object color attribute to given color.
     * @param color
     */
    public void setColor(int color) {
        // TODO: set the current color
    }

    /**
     * Takes in color integer values [0-255], returns corresponding color-int value.
     * @param red
     * @param green
     * @param blue
     */
    public static int getColorEncoding(int red, int green, int blue) {
        // TODO: provide rgb color encoding
        return 0;
    }

    /**
     * Returns the RGB value representing the current color.
     * @return integer RGB value
     */
    public int getColor() {
        // TODO return the current color setting
        return 0;
    }

    /**
     * Takes in rectangle params, fills rectangle in canvas based on these.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void fillRect(int x, int y, int width, int height) {
        // draw a filled rectangle on the canvas, requires decision on its color
    }

    /**
     * Takes in polygon params, fills polygon in canvas based on these.
     * Paint is always that for corn.
     * @param xPoints
     * @param yPoints
     * @param nPoints
     */
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints){
        // translate the points into a path
        // draw a path on the canvas
    }

    /**
     * Takes in line params, draws line in canvas based on these.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void drawLine(int x1, int y1, int x2, int y2) {
        // TODO: draw a line on the canvas
    }

    /**
     * Takes in oval params, fills oval in canvas based on these.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void fillOval(int x, int y, int width, int height) {
        // TODO: draw an oval on the canvas
    }

}