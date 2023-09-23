package com.emotiontracker.presentation.choice

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
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

    private var sectorPaint = Paint()
    private var centerPaint = Paint()
    private var framePaint = Paint()

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

    private val sectors = listOf(
        SectorParams(
            Emotion.Fearing.Fear(),
            338.5f,
            R.color.fear,
            BitmapFactory.decodeResource(resources, R.drawable.fear)
        ),
        SectorParams(
            Emotion.Surprising.Surprise(),
            23.5f,
            R.color.surprise,
            BitmapFactory.decodeResource(resources, R.drawable.surprise)
        ),
        SectorParams(
            Emotion.Sadness.Sad(),
            68.5f,
            R.color.sad,
            BitmapFactory.decodeResource(resources, R.drawable.sad)
        ),
        SectorParams(
            Emotion.Aversion.Dislike(),
            113.5f,
            R.color.dislike,
            BitmapFactory.decodeResource(resources, R.drawable.dislike)
        ),
        SectorParams(
            Emotion.Angry.Anger(),
            158.5f,
            R.color.angry,
            BitmapFactory.decodeResource(resources, R.drawable.angry)
        ),
        SectorParams(
            Emotion.Interesting.Anticipation(),
            203.5f,
            R.color.interest,
            BitmapFactory.decodeResource(resources, R.drawable.interest)
        ),
        SectorParams(
            Emotion.Glad.Joy(),
            248.5f,
            R.color.joy,
            BitmapFactory.decodeResource(resources, R.drawable.glad)
        ),
        SectorParams(
            Emotion.Trusting.Trust(),
            293.5f,
            R.color.trust,
            BitmapFactory.decodeResource(resources, R.drawable.trust)
        )
    )

    private fun initPaint() {
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
    }

    private fun initSectorPaint(sectorColor: Int): Paint {
        sectorPaint.apply {
            isAntiAlias = true
            color = resources.getColor(sectorColor, null)
            style = Paint.Style.FILL
            strokeWidth = 4f
        }
        return sectorPaint
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
        radius = diameter / 2f
        rect.left = paddingLeft.toFloat() + 10f
        rect.top = paddingTop.toFloat() + 10f
        rect.right = rect.left + diameter.toFloat() - 20f
        rect.bottom = rect.top + diameter.toFloat() - 20f

        smallRadius = diameter / 5f
        centerX = diameter / 2f
        centerY = centerX

        smallRect.left = rect.left + radius - smallRadius
        smallRect.top = rect.top + radius - smallRadius
        smallRect.right = rect.right - radius + smallRadius
        smallRect.bottom = rect.bottom - radius + smallRadius

        setMeasuredDimension(diameter, diameter)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        /* Рисуем секторы */
        sectors.forEach {
            /* Рисуем круг из секторов */
            val paint = initSectorPaint(it.color)
            canvas.drawArc(rect, it.sectorAngel, sweepAngel, true, paint)
            /* Рисуем смайлы */
            val leftOfIcon = centerX + (
                    (radius + smallRadius) / 2 * cos(
                        Math.toRadians((it.sectorAngel + sweepAngel / 2).toDouble())
                    )
                    ) - (it.emoji.width) / 2
            val topOfIcon = centerY + (
                    (radius + smallRadius) / 2 * sin(
                        Math.toRadians((it.sectorAngel + sweepAngel / 2).toDouble())
                    )
                    ) - (it.emoji.height) / 2
            canvas.drawBitmap(
                it.emoji,
                leftOfIcon.toFloat(),
                topOfIcon.toFloat(),
                null
            )
        }

        initPaint()

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
        val getSectorName: String?
        if (isInsideCircle(event.x, event.y)) {
            when (event.action) {

                MotionEvent.ACTION_DOWN -> {
                    return if (getSector(event) != null) {
                        getSectorName = getSector(event).let { it!!::class.simpleName }
                        vibrate()
                        getEmotionName = getSectorName
                        true
                    } else false
                }

                MotionEvent.ACTION_MOVE -> {
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

        var currentAngle =
            -Math.toDegrees(atan2(height / 2 - event.y, event.x - width / 2).toDouble())
        if (currentAngle < 0) currentAngle += 360

        sectors.forEach {
            when (currentAngle) {
                in (it.sectorAngel)..(it.sectorAngel + sweepAngel) -> {
                    startAngle = it.sectorAngel
                    invalidate()
                    emotion = it.emotion
                    onTouchResult?.invoke(emotion!!)
                }
            }
        }
        return emotion
    }

    private fun isInsideCircle(x: Float, y: Float): Boolean {
        val dx = x - width / 2f
        val dy = y - height / 2f
        return (dx * dx + dy * dy <= (width / 2f) * (width / 2f))
    }

    private fun vibrate() {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(30)
    }

    companion object {
        data class SectorParams(
            val emotion: Emotion,
            val sectorAngel: Float,
            val color: Int,
            val emoji: Bitmap
        )
    }
}

