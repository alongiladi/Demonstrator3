package com.example.demonstrator3

import android.app.Application
import com.example.demonstrator3.data.ShoppingDatabase
import com.example.demonstrator3.data.ShoppingRepository

class ShoppingApplication : Application() {

    // יוצרים אובייקט דיבי חדש באמצעות הקובץ דיבי שכתבנו
    //(שבאמצעות ספריית רום יוצר דיבי חדש עם קוד שגוגל כתבו)

    // by lazy אומר: "אל תצור את זה מיד על ההתחלה, אלא רק בפעם הראשונה שמישהו יבקש את זה".
    // זה חוסך זמן בעליית האפליקציה.
    val database by lazy { ShoppingDatabase.getDatabase(this) }


    ////ואז אנחנו יוצרים את החיבור לדיבי הזה, קוראים לו רפוסטורי, ומשייכים אליו ישירות תאת הדיבי + הקובץ דאו שיצרנו
    val repository by lazy { ShoppingRepository(database.shoppingDao()) }
}