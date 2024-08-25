package ru.german.android.expertcourseunscrambleword.views.scrambleword

    import android.content.Context
    import android.util.AttributeSet
    import androidx.appcompat.widget.AppCompatTextView

class ScrambleTextView : AppCompatTextView, UpdateText {
        
        constructor(context: Context) : super(context)
        constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
        constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
            context,
            attributeSet,
            defStyleAttr
        )

    override fun getFreezesText(): Boolean = true

    override fun update(text: String) {
        this.text = text
    }

}

interface UpdateText {

    fun update(text: String)
}