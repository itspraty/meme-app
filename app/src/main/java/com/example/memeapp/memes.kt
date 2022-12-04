package com.example.memeapp

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class memes : AppCompatActivity() {
    var currentImageUrl:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memes)

        loadMeme()
        val shareBtn=findViewById<Button>(R.id.shareBtn)
        shareBtn.setOnClickListener{
       val intent=Intent(Intent.ACTION_SEND)
            intent.type="text/plain"
         intent.putExtra(Intent.EXTRA_TEXT,"HEY! SEE THIS COOL MEME $currentImageUrl")
         val chooser=Intent.createChooser(intent,"share this meme using...")
            startActivity(chooser)
        }


        val nextBtn=findViewById<Button>(R.id.nextBtn)
        nextBtn.setOnClickListener{
            loadMeme()
        }
    }
    private fun loadMeme(){
        val barProgress=findViewById<ProgressBar>(R.id.barProgress)
        barProgress.visibility= View.VISIBLE
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"


        val jsonObjectRequest  = JsonObjectRequest(
            Request.Method.GET, url,null,
            { response ->
           currentImageUrl=response.getString("url")
                val imageView2=findViewById<ImageView>(R.id.imageView2)
                Glide.with(this).load(currentImageUrl).listener(object: RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                    barProgress.visibility=View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        barProgress.visibility=View.GONE
                        return false

                    }

                }).into(imageView2)
            },
            Response.ErrorListener { Toast.makeText(this, "something went wrong", Toast.LENGTH_LONG) })

        queue.add(jsonObjectRequest)
    }







}