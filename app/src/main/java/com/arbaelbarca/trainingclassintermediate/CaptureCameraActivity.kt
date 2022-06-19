package com.arbaelbarca.trainingclassintermediate

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import pl.aprilapps.easyphotopicker.*
import java.util.jar.Manifest

class CaptureCameraActivity : AppCompatActivity() {

    lateinit var imgCapture: ImageView
    lateinit var btnCamera: Button
    lateinit var btnGalery: Button
    lateinit var btnDocument: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capture_camera)

        imgCapture = findViewById(R.id.imgCaptureView)
        btnCamera = findViewById(R.id.btnFromCamera)
        btnGalery = findViewById(R.id.btnFromGalery)
        btnDocument = findViewById(R.id.btnFromDocument)

        initial()

    }

    private fun initial() {

        initCallDexter()

        btnCamera.setOnClickListener {
            callEasyImage()
                .build()
                .openCameraForImage(this)
        }

        btnGalery.setOnClickListener {
            callEasyImage()
                .build()
                .openGallery(this)
        }

        btnDocument.setOnClickListener {
            callEasyImage()
                .build()
                .openDocuments(this)
        }
    }


    fun initCallDexter() {
        Dexter.withContext(this)
            .withPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                    if (p0!!.areAllPermissionsGranted()) {
                        Toast.makeText(
                            this@CaptureCameraActivity,
                            "Anda diijinkan",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else Toast.makeText(
                        this@CaptureCameraActivity,
                        "Anda di tolak",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                }

            }).onSameThread()
            .check()
    }

    fun callEasyImage(): EasyImage.Builder {
        return EasyImage.Builder(this)
            .allowMultiple(true)
            .setCopyImagesToPublicGalleryFolder(true)
            .setChooserType(ChooserType.CAMERA_AND_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callEasyImage()
            .build()
            .handleActivityResult(requestCode, resultCode, data, this, object : DefaultCallback() {
                override fun onMediaFilesPicked(imageFiles: Array<MediaFile>, source: MediaSource) {
                    val getBitmap = BitmapFactory.decodeFile(imageFiles[0].file.absolutePath)
                    imgCapture.setImageBitmap(getBitmap)
                    Toast.makeText(
                        this@CaptureCameraActivity,
                        "Name file ${source.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onImagePickerError(error: Throwable, source: MediaSource) {
                    error.printStackTrace()
                }
            })
    }

}