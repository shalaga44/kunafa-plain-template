package com.narbase.narcore

import com.narbase.kunafa.core.components.Page
import com.narbase.kunafa.core.components.page
import com.narbase.kunafa.core.components.textView
import com.narbase.kunafa.core.components.verticalLayout
import com.narbase.kunafa.core.css.height
import com.narbase.kunafa.core.css.overflow
import com.narbase.kunafa.core.css.width
import com.narbase.kunafa.core.dimensions.vh
import com.narbase.kunafa.core.dimensions.vw


fun main() {
    page {
        Page.title = "Narcore"

        style {
            overflow = "hidden"
            width = 100.vw
            height = 100.vh
        }
        mount(verticalLayout {
            textView {
                text = "HelloWorld"
            }
        })
    }

}