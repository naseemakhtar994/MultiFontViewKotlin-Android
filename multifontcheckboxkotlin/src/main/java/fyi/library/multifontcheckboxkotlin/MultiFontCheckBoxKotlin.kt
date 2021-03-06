package fyi.library.multifontcheckboxkotlin

import android.content.Context
import android.util.AttributeSet
import android.util.Log


class MultiFontCheckBoxKotlin : android.support.v7.widget.AppCompatCheckBox {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initializeCustomTypeface(attrs, context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initializeCustomTypeface(attrs, context)
    }

    private fun initializeCustomTypeface(attrs: AttributeSet?, context: Context) {
        if (attrs != null) {

            val attributesArray = context.obtainStyledAttributes(attrs,
                    R.styleable.MultiFontCheckBoxKotlin)
            val fontID = attributesArray.getInt(R.styleable.MultiFontCheckBoxKotlin_typeface_from_list, -1)
            val fontStringArray = context.resources.getStringArray(R.array.customFonts)

            //if a typeface is selected for the Button and if the font names are declared in the
            // string array customFonts then load that typeface from cache
            if (fontID != -1 && fontStringArray.isNotEmpty()) {
                try {
                    val myTypeface = FontLoader.getTypeface(context, fontStringArray[fontID])
                    typeface = myTypeface
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            } else {
                Log.e(LOG_TAG, "Either of the string array CustomFonts or" +
                        " attribute typeface_from_list is not present" + fontID)
            }
            attributesArray.recycle()
        }

    }

    companion object {

        private val LOG_TAG = "MultiFontCheckBoxKotlin"
    }
}
