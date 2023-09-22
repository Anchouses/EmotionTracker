package com.emotiontracker.presentation.choice

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.os.Vibrator
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.emotiontracker.R
import com.emotiontracker.presentation.datasource.Emotion
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class CircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private var centerPaint = Paint()
    private var framePaint = Paint()
    private var angryPaint = Paint()
    private var joyPaint = Paint()
    private var sadPaint = Paint()
    private var trustPaint = Paint()
    private var surprisePaint = Paint()
    private var interestPaint = Paint()
    private var fearPaint = Paint()
    private var dislikePaint = Paint()

    private var startAngle = 0f
    private val sweepAngel = 43f
    private var radius = 0f
    private var smallRadius = 0f
    private var centerX = 0f
    private var centerY = 0f
    private val rect = RectF(0f, 0f, 0f, 0f)
    private val smallRect = RectF(0f, 0f, 0f, 0f)

    private var getEmotionName: String? = null
    var onTouchResult: ((emotion: Emotion) -> Unit)? = null

    private val fearPair = Pair(338.5f, BitmapFactory.decodeResource(resources, R.drawable.fear))
    private val surprisePair = Pair(23.5f, BitmapFactory.decodeResource(resources, R.drawable.surprise))
    private val sadPair = Pair(68.5f, BitmapFactory.decodeResource(resources, R.drawable.sad))
    private val dislikePair = Pair(113.5f, BitmapFactory.decodeResource(resources, R.drawable.dislike))
    private val angryPair = Pair(158.5f, BitmapFactory.decodeResource(resources, R.drawable.angry))
    private val interestPair = Pair(203.5f, BitmapFactory.decodeResource(resources, R.drawable.interest))
    private val gladPair = Pair(248.5f, BitmapFactory.decodeResource(resources, R.drawable.glad))
    private val trustPair = Pair(293.5f, BitmapFactory.decodeResource(resources, R.drawable.trust))

    private val emoji = listOf(
        fearPair,
        surprisePair,
        sadPair,
        dislikePair,
        sadPair,
        angryPair,
        interestPair,
        gladPair,
        trustPair
    )

    private fun initPaint(){

        centerPaint.apply {
            isAntiAlias = true
            color = resources.getColor(R.color.white, null)
            style = Paint.Style.FILL
        }

        framePaint.apply {
            isAntiAlias = true
            color = resources.getColor(R.color.frame, null)
            style = Paint.Style.STROKE
            strokeWidth = 10f
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }

        angryPaint.apply {
            isAntiAlias = true
            color = resources.getColor(R.color.angry, null)
            style = Paint.Style.FILL
            strokeWidth = 4f
        }

        joyPaint.apply {
            isAntiAlias = true
            color = resources.getColor(R.color.joy, null)
            style = Paint.Style.FILL
            strokeWidth = 4f
        }

        fearPaint.apply {
            isAntiAlias = true
            color = resources.getColor(R.color.fear, null)
            style = Paint.Style.FILL
            strokeWidth = 4f
        }

        surprisePaint.apply {
            isAntiAlias = true
            color = resources.getColor(R.color.surprise, null)
            style = Paint.Style.FILL
            strokeWidth = 4f
        }

        sadPaint.apply {
            isAntiAlias = true
            color = resources.getColor(R.color.sad, null)
            style = Paint.Style.FILL
            strokeWidth = 4f
        }

        dislikePaint.apply {
            isAntiAlias = true
            color = resources.getColor(R.color.dislike, null)
            style = Paint.Style.FILL
            strokeWidth = 4f
        }

        interestPaint.apply {
            isAntiAlias = true
            color = resources.getColor(R.color.interest, null)
            style = Paint.Style.FILL
            strokeWidth = 4f
        }

        trustPaint.apply {
            isAntiAlias = true
            color = resources.getColor(R.color.trust, null)
            style = Paint.Style.FILL
            strokeWidth = 4f
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredRadius = 300

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> min(desiredRadius, widthSize)
            else -> desiredRadius
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> min(desiredRadius, heightSize)
            else -> desiredRadius
        }

        val diameter = min(width, height)
        radius = diameter/2f
        rect.left = paddingLeft.toFloat() + 10f
        rect.top = paddingTop.toFloat() + 10f
        rect.right = rect.left + diameter.toFloat() - 20f
        rect.bottom = rect.top + diameter.toFloat() - 20f

        smallRadius = diameter/5f
        centerX = diameter/2f
        centerY = centerX

        smallRect.left = rect.left + radius - smallRadius
        smallRect.top = rect.top + radius - smallRadius
        smallRect.right  = rect.right - radius + smallRadius
        smallRect.bottom  = rect.bottom - radius + smallRadius

        setMeasuredDimension(diameter, diameter)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        initPaint()

        /* Рисуем круг из секторов */
        canvas.drawArc(rect, 23.5f, sweepAngel, true, surprisePaint)
        canvas.drawArc(rect, 68.5f, sweepAngel, true, sadPaint)
        canvas.drawArc(rect, 113.5f, sweepAngel, true, dislikePaint)
        canvas.drawArc(rect, 158.5f, sweepAngel, true, angryPaint)
        canvas.drawArc(rect, 203.5f, sweepAngel, true, interestPaint)
        canvas.drawArc(rect, 248.5f, sweepAngel, true, joyPaint)
        canvas.drawArc(rect, 293.5f, sweepAngel, true, trustPaint)
        canvas.drawArc(rect, 338.5f, sweepAngel, true, fearPaint)

        /* Рисуем смайлы */
        for (i in emoji.indices) {
            val angel = emoji[i].component1()
            val bitmap = emoji[i].component2()
            val leftOfIcon = centerX + (
                    (radius + smallRadius) / 2 * cos(
                        Math.toRadians((angel + sweepAngel / 2).toDouble())
                    )
                    ) - (bitmap.width) / 2

            val topOfIcon = centerY + (
                    (radius + smallRadius) / 2 * sin(
                        Math.toRadians((angel + sweepAngel / 2).toDouble())
                    )
                    ) - (bitmap.height) / 2
            canvas.drawBitmap(
                bitmap,
                leftOfIcon.toFloat(),
                topOfIcon.toFloat(),
                null
            )
        }

        /* Рисуем рамку */
        if (startAngle != 0f) {
            canvas.drawArc(rect, startAngle, sweepAngel, true, framePaint)
            canvas.drawArc(smallRect, startAngle, sweepAngel, true, framePaint)
        }

        /* Рисуем внутренний круг */
        canvas.drawCircle(centerX, centerY, smallRadius - 15f, centerPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var getSectorName: String? = ""
        if (isInsideCircle(event.x, event.y)) {
            when (event.action) {

                MotionEvent.ACTION_DOWN -> {
                    getSector(event)
                    return if (getSector(event) != null) {
                        getSectorName = getSector(event).let { it!!::class.simpleName }
                        vibrate()
                        getEmotionName = getSectorName
                        true
                    } else false
                }

                MotionEvent.ACTION_MOVE -> {
                    getSector(event)
                    return if (getSector(event) != null) {
                        getSectorName = getSector(event).let { it!!::class.simpleName }
                        if (getEmotionName != getSectorName) {
                            vibrate()
                        }
                        getEmotionName = getSectorName
                        true
                    } else false
                }
            }
        } else return false

        return super.onTouchEvent(event)
    }

    private fun getSector(event: MotionEvent): Emotion? {
        var emotion: Emotion? = null
        var angle = -Math.toDegrees(atan2(height/2 - event.y, event.x - width/2).toDouble())
        if (angle < 0) angle += 360
        when (angle) {
            in 0.0..21.5 -> {
                startAngle = 338.5F
                invalidate()
                emotion = Emotion.Fearing.Fear()
                onTouchResult?.invoke(emotion)
            }
            in 23.5..66.5 -> {
                startAngle = 23.5F
                invalidate()
                emotion = Emotion.Surprising.Surprise()
                onTouchResult?.invoke(emotion)
            }
            in 68.5..111.5 -> {
                startAngle = 68.5F
                invalidate()
                emotion =  Emotion.Sadness.Sad()
                onTouchResult?.invoke(emotion)
            }
            in 113.5..156.5 -> {
                startAngle = 113.5F
                invalidate()
                emotion =  Emotion.Aversion.Dislike()
                onTouchResult?.invoke(emotion)
            }
            in 158.5..201.5 -> {
                startAngle = 158.5F
                invalidate()
                emotion =  Emotion.Angry.Anger()
                onTouchResult?.invoke(emotion)
            }
            in 203.5..246.5 -> {
                startAngle = 203.5F
                invalidate()
                emotion =  Emotion.Interesting.Anticipation()
                onTouchResult?.invoke(emotion)
            }
            in 248.5..291.5 -> {
                startAngle = 248.5F
                invalidate()
                emotion =  Emotion.Glad.Joy()
                onTouchResult?.invoke(emotion)
            }
            in 293.5..336.5 -> {
                startAngle = 293.5F
                invalidate()
                emotion = Emotion.Trusting.Trust()
                onTouchResult?.invoke(emotion)
            }
            in 338.5..360.0 -> {
                startAngle = 338.5F
                invalidate()
                emotion =  Emotion.Fearing.Fear()
                onTouchResult?.invoke(emotion)
            }
        }
        return emotion
    }

    private fun isInsideCircle(x: Float, y: Float): Boolean{
        val dx = x - width/2f
        val dy = y - height/2f
        return (dx*dx + dy*dy <= (width/2f)*(width/2f))
    }

    private fun vibrate(){
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(30)
    }
}

