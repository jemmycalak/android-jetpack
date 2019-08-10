package id.co.aygo.androidjetpack.utils.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.View

import androidx.appcompat.widget.AppCompatImageView

class CircleImageView : AppCompatImageView {

    private var borderWidth = 4
    private var viewWidth: Int = 0
    private var viewHeight: Int = 0
    private var image: Bitmap? = null
    private var paint: Paint? = null
    private var paintBorder: Paint? = null
    private var shader: BitmapShader? = null

    constructor(context: Context) : super(context) {
        setup()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setup()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup()
    }

    private fun setup() {
        paint = Paint()
        paint!!.isAntiAlias = true

        paintBorder = Paint()
        setBorderColor(Color.WHITE)
        paintBorder!!.isAntiAlias = true
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, paintBorder)

        paintBorder!!.setShadowLayer(4.0f, 0.0f, 2.0f, Color.WHITE)
    }

    fun setBorderWidth(borderWidth: Int) {
        this.borderWidth = borderWidth
        this.invalidate()
    }

    fun setBorderColor(borderColor: Int) {
        if (paintBorder != null)
            paintBorder!!.color = borderColor

        this.invalidate()
    }

    private fun loadBitmap() {
        val bitmapDrawable = this.drawable as BitmapDrawable

        if (bitmapDrawable != null)
            image = bitmapDrawable.bitmap
    }

    @SuppressLint("DrawAllocation")
    public override fun onDraw(canvas: Canvas) {
        loadBitmap()

        if (image != null) {
            shader = BitmapShader(
                Bitmap.createScaledBitmap(image!!, canvas.width, canvas.height, false),
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
            )
            paint!!.shader = shader
            val circleCenter = viewWidth / 2
            canvas.drawCircle(
                (circleCenter + borderWidth).toFloat(),
                (circleCenter + borderWidth).toFloat(),
                circleCenter + borderWidth - 4.0f,
                paintBorder!!
            )
            canvas.drawCircle(
                (circleCenter + borderWidth).toFloat(),
                (circleCenter + borderWidth).toFloat(),
                circleCenter - 4.0f,
                paint!!
            )
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = measureWidth(widthMeasureSpec)
        val height = measureHeight(heightMeasureSpec, widthMeasureSpec)

        viewWidth = width - borderWidth * 2
        viewHeight = height - borderWidth * 2

        setMeasuredDimension(width, height)
    }

    private fun measureWidth(measureSpec: Int): Int {
        var result = 0
        val specMode = View.MeasureSpec.getMode(measureSpec)
        val specSize = View.MeasureSpec.getSize(measureSpec)

        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            // Measure the text
            result = viewWidth
        }

        return result
    }

    private fun measureHeight(measureSpecHeight: Int, measureSpecWidth: Int): Int {
        var result = 0
        val specMode = View.MeasureSpec.getMode(measureSpecHeight)
        val specSize = View.MeasureSpec.getSize(measureSpecHeight)

        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = viewHeight
        }

        return result + 2
    }
}
