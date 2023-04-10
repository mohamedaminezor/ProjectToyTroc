package com.mobelite.toytroc.ExpandableRecyclerView

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mobelite.toytroc.R
import com.mobelite.toytroc.databinding.ActivityExpandableRecyclerBinding

class ExpandableRecycler : AppCompatActivity() {
    private var childItem1 = ArrayList<ChildItem>()
    private var childItem2 = ArrayList<ChildItem>()
    private lateinit var binding : ActivityExpandableRecyclerBinding
    private var firebaseDatabase : FirebaseDatabase? = null
    private var dataReference : DatabaseReference? = null
    private lateinit var parentRecyclerView: RecyclerView
    private lateinit var parentList : ArrayList<ParentItem>

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_expandable_recycler)

        firebaseDatabase = FirebaseDatabase.getInstance()
        dataReference = firebaseDatabase!!.getReference("Category")


        parentRecyclerView = findViewById(R.id.parentRecyclerView)
        parentRecyclerView.setHasFixedSize(true)
        parentRecyclerView.layoutManager = LinearLayoutManager(this)
        parentList = ArrayList()
        PrepareData()
        val adapter = ParentRecyclerViewAdapter(parentList,this)
        parentRecyclerView.adapter = adapter
        dataReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    val categorytoys= arrayOf(getString(R.string.Action_figures),getString(R.string.cars),getString(R.string.creative_toys),getString(R.string.musical_toys),getString(R.string.stuffed_toys))
                    val categoryvideo= arrayOf(getString(R.string.action),getString(R.string.horror),getString(R.string.moba))
                    for (i in categorytoys){
                                 var x1= snapshot.child(getString(R.string.toys)).child(i)
                                 var xtitle=x1.child(getString(R.string.title)).getValue(String::class.java)
                                 var ximage=x1.child(getString(R.string.image)).getValue(String::class.java)

                             childItem1.add(ChildItem(xtitle!!,ximage!!))
                    }
                                val toysData = snapshot.child(getString(R.string.toys)).child(getString(R.string.toysdata))
                                val toysTitle = toysData.child(getString(R.string.title)).getValue(String::class.java)
                                val toysImage = toysData.child(getString(R.string.image)).getValue(String::class.java)

                            parentList.add(ParentItem(toysTitle!!,toysImage!!,childItem1))


                    for (j in categoryvideo){
                        var x2= snapshot.child(getString(R.string.videogames)).child(j)
                        var xtitle=x2.child(getString(R.string.title)).getValue(String::class.java)
                        var ximage=x2.child(getString(R.string.image)).getValue(String::class.java)

                    childItem2.add(ChildItem(xtitle!!,ximage!!))
                    }

                        val videogamesData = snapshot.child(getString(R.string.videogames)).child(getString(R.string.videogamesdata))
                        val videogamesTitle = videogamesData.child(getString(R.string.title)).getValue(String::class.java)
                        val videogamesImage = videogamesData.child(getString(R.string.image)).getValue(String::class.java)

                    parentList.add(ParentItem(videogamesTitle!!,videogamesImage!!,childItem2))




                }
    }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })}
    private fun PrepareData(){

        val childItem2 = ArrayList<ChildItem>()
        childItem2.add(ChildItem("",""))
        parentList.add(ParentItem("Choose from Toys/videogame","",childItem2))

    }
}
