package com.arbaelbarca.trainingclassintermediate

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import pl.aprilapps.easyphotopicker.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CaptureCameraFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CaptureCameraFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var imgCapture: ImageView
    lateinit var btnCamera: Button
    lateinit var btnGalery: Button
    lateinit var btnDocument: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_capture_camera, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CaptureCameraFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgCapture = view.findViewById(R.id.imgCaptureView)
        btnCamera = view.findViewById(R.id.btnFromCamera)
        btnGalery = view.findViewById(R.id.btnFromGalery)
        btnDocument = view.findViewById(R.id.btnFromDocument)

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
        Dexter.withContext(requireContext())
            .withPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                    if (p0!!.areAllPermissionsGranted()) {
                        Toast.makeText(
                            requireContext(),
                            "Anda diijinkan",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else Toast.makeText(
                        requireContext(),
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
        return EasyImage.Builder(requireContext())
            .allowMultiple(false)
            .setChooserTitle("Easy contoh")
            .setFolderName("Eeast test")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callEasyImage()
            .build()
            .handleActivityResult(
                requestCode,
                resultCode,
                data,
                requireActivity(),
                object : DefaultCallback() {
                    override fun onMediaFilesPicked(
                        imageFiles: Array<MediaFile>,
                        source: MediaSource
                    ) {
                        val getBitmap = BitmapFactory.decodeFile(imageFiles[0].file.absolutePath)
                        val getUri = Uri.fromFile(imageFiles[0].file)
//                        imgCapture.setImageBitmap(getBitmap)
                        imgCapture.setImageURI(getUri)
                        Toast.makeText(
                            requireContext(),
                            "Name file fragment ${source.name}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onImagePickerError(error: Throwable, source: MediaSource) {
                        error.printStackTrace()
                        Toast.makeText(
                            requireContext(),
                            "eerror ${error.printStackTrace()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onCanceled(source: MediaSource) {
                        Toast.makeText(
                            requireContext(),
                            "canceel ${source.name}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
    }

}