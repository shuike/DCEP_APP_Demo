package com.shuike.demo

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout

class BackgroundView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {

    private var child1: View
    private var child2: View

    init {
        LayoutInflater.from(context).inflate(R.layout.view_background, this, true)
        child1 = findViewById(R.id.child1)
        child2 = findViewById(R.id.child2)
    }

    private var offSet: Float = 0f

    // Child 1
    private var leftOffset1: Int = 0 // 左边的偏移量1
    private var leftOffset2: Int = 0 // 左边的偏移量2

    // Child 2
    private var rightOffset1: Int = 0 // 右边的偏移量1
    private var rightOffset2: Int = 0 // 右边的偏移量2

    private var isEventNum = false
    fun onPageScrolled(positionOffset: Float, position: Int) {
        isEventNum = (position + 1) % 2 == 0

        offSet = positionOffset * 1.2f
        val int = (measuredWidth * (1f - offSet)).toInt()
        if (!isEventNum) { // 奇数
            leftOffset2 = int
            rightOffset2 = measuredWidth

            rightOffset1 = leftOffset2
            leftOffset1 = 0
        } else { // 偶数
            if (leftOffset2 != 0) {
                leftOffset2 = 0
            }
            rightOffset2 = int

            leftOffset1 = rightOffset2
            rightOffset1 = measuredWidth
        }
        invalidate()
    }

    override fun drawChild(canvas: Canvas?, child: View?, drawingTime: Long): Boolean {
        canvas ?: return super.drawChild(canvas, child, drawingTime)
        canvas.save()
        when (child) {
            child1 -> { // 子view1绘制处理
                canvas.clipRect(leftOffset1, 0, rightOffset1, measuredHeight)
            }

            child2 -> { // 子view2绘制处理
                canvas.clipRect(leftOffset2, 0, rightOffset2, measuredHeight)
            }
        }
        val drawChildResult = super.drawChild(canvas, child, drawingTime)
        canvas.restore()
        return drawChildResult
    }
}