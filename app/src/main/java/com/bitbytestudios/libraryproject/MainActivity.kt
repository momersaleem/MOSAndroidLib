package com.bitbytestudios.libraryproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bitbytestudios.mosandroidlib.imageselection.GallerySelection
import com.bitbytestudios.mosandroidlib.imageselection.ImageSelection
import com.bitbytestudios.mosandroidlib.imageselection.ImageSelector

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ImageSelector.getInstance().openSelector(this)
    }
}
