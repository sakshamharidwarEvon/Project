package com.example.task_2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task_2.ModelClass.Companion.AD_LAYOUT
import com.example.task_2.ModelClass.Companion.IMAGE_LAYOUT
import com.example.task_2.ModelClass.Companion.USER_INFO_LAYOUT
import java.util.*

class MainActivity() : AppCompatActivity(), RecyclerViewClickInterface, Parcelable {
    private var recyclerView: RecyclerView? = null
    var isScrolling = false
    var currentItem = 0
    var totalItem:Int = 0
    var scrollOutItem:Int = 0
    var modelClassList: MutableList<ModelClass>? = null
    var adapter: Adapter? = null
    var progressBar: ProgressBar? = null
    private val isLoading = false
    private val isLastPage = false

    constructor(parcel: Parcel) : this() {
        isScrolling = parcel.readByte() != 0.toByte()
        currentItem = parcel.readInt()
        totalItem = parcel.readInt()
        scrollOutItem = parcel.readInt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        progressBar = findViewById<View>(R.id.progress) as ProgressBar
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val rvContacts = findViewById<View>(R.id.recycler_view) as RecyclerView
        rvContacts.setLayoutManager(layoutManager)

//        List<ModelClass>
        modelClassList = ArrayList()
        (modelClassList as ArrayList<ModelClass>).add(
            ModelClass(
                AD_LAYOUT,
                "Click to  Open the Browser"
            )
        )
        (modelClassList as ArrayList<ModelClass>).add(ModelClass(AD_LAYOUT, "Click Here"))
        (modelClassList as ArrayList<ModelClass>).add(
            ModelClass(
                USER_INFO_LAYOUT,
                R.drawable.ic_launcher_background,
                "Click Here",
                "Evon"
            )
        )
        //modelClassList.add(new ModelClass(IMAGE_LAYOUT,R.mipmap.ic_launcher));
        (modelClassList as ArrayList<ModelClass>).add(
            ModelClass(
                AD_LAYOUT,
                "Click to  Open the Browser"
            )
        )
        (modelClassList as ArrayList<ModelClass>).add(ModelClass(AD_LAYOUT, "Click Here"))
        (modelClassList as ArrayList<ModelClass>).add(
            ModelClass(
                USER_INFO_LAYOUT,
                R.drawable.ic_launcher_background,
                "Click Here",
                "Evon"
            )
        )
        (modelClassList as ArrayList<ModelClass>).add(
            ModelClass(
                AD_LAYOUT,
                "Click to  Open the Browser"
            )
        )
        (modelClassList as ArrayList<ModelClass>).add(ModelClass(AD_LAYOUT, "hello"))
        (modelClassList as ArrayList<ModelClass>).add(
            ModelClass(
                USER_INFO_LAYOUT,
                R.drawable.ic_launcher_background,
                "Click Here",
                "Evon"
            )
        )
        (modelClassList as ArrayList<ModelClass>).add(
            ModelClass(
                AD_LAYOUT,
                "Click to  Open the Browser"
            )
        )
        (modelClassList as ArrayList<ModelClass>).add(ModelClass(AD_LAYOUT, "Click Here"))
        (modelClassList as ArrayList<ModelClass>).add(
            ModelClass(
                USER_INFO_LAYOUT,
                R.drawable.ic_launcher_background,
                "Click Here ",
                "Evon"
            )
        )
        (modelClassList as ArrayList<ModelClass>).add(
            ModelClass(
                IMAGE_LAYOUT,
                R.mipmap.ic_launcher
            )
        )

//        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"user one"));com/example/task/ModelClass.java:18
//        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"user two"));
//        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"user three"));
//        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"user four"));
//        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"user five"));
//        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"user six"));
//        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"user seven"));
//        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"user eight"));
//        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"user nine"));

//      //Adapter
        adapter = Adapter(modelClassList as ArrayList<ModelClass>, this)
        rvContacts.setAdapter(adapter)
        adapter!!.notifyDataSetChanged()
        rvContacts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItem = layoutManager.childCount
                totalItem = layoutManager.itemCount
                Log.e("value of total item", "" + totalItem)
                scrollOutItem = layoutManager.findFirstVisibleItemPosition()
                if (isScrolling && currentItem + scrollOutItem == totalItem) {
                    isScrolling = false
                    fetchData()
                }
            }
        })
    }

    private fun fetchData() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
        for (i in 0..4) {
            modelClassList!!.add(ModelClass(AD_LAYOUT, "hello"))
            modelClassList!!.add(ModelClass(AD_LAYOUT, "hello"))
            modelClassList!!.add(
                ModelClass(
                    USER_INFO_LAYOUT,
                    R.drawable.ic_launcher_background,
                    "hello",
                    "hi"
                )
            )
            Log.e("inside fetch data", "oooooooommmmmmm")
            adapter!!.notifyDataSetChanged()
            progressBar!!.visibility = View.GONE
        }
    }
//        }),5000);
//    }

    //        }),5000);
    //    }
    override fun onItemClick(position: Int) {
        //if (position > 0 && (position%2)== 0){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
        startActivity(browserIntent)
        Toast.makeText(this, "Hello Browser", Toast.LENGTH_LONG).show()
        Log.e("Value of position", "" + position)

//    }else{
//
//            Intent intent = new Intent(this, Info.class);
//            startActivity(intent);
//        }
    }

    override fun onTextClick(position: Int) {
        val intent = Intent(this, Info::class.java)
        startActivity(intent)
    }

//    @Override
//    public void onLongItemClick(int position) {
//
//    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isScrolling) 1 else 0)
        parcel.writeInt(currentItem)
        parcel.writeInt(totalItem)
        parcel.writeInt(scrollOutItem)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }
}