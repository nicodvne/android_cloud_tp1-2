package com.example.ndav_androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ndav_androidapp.Adapter.AppleVersionAdapter
import com.example.ndav_androidapp.databinding.ActivityRecyclerViewBinding
import com.example.ndav_androidapp.models.DataSourceFooterSample
import com.example.ndav_androidapp.models.DataSourceHeaderSample
import com.example.ndav_androidapp.models.DataSourceSample
import com.example.ndav_androidapp.models.MyObjectForRecyclerView

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var appleAdapter: AppleVersionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appleAdapter = AppleVersionAdapter{item, view -> onItemClick(item, view)}

        // We define the type of linear layout
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.recyclerView.adapter = appleAdapter

        appleAdapter.submitList(generateFakeDatas());
    }

    fun generateFakeDatas(): MutableList<MyObjectForRecyclerView> {

        val result = mutableListOf<MyObjectForRecyclerView>()

        mutableListOf(
            DataSourceSample("Iphone 14 pro max", 2022),
            DataSourceSample("MacBook Pro M2", 2021),
            DataSourceSample("Iphone 13 pro max", 2021),
            DataSourceSample("Iphone 12 pro max", 2020),
            DataSourceSample("MacBook Air", 2012),
            DataSourceSample("Apple Watch Max", 2022),
            DataSourceSample("Ipad Pro", 2022),
            DataSourceSample("Iphone SE", 2018),
            DataSourceSample("Iphone SE", 2018),
            DataSourceSample("Iphone SE", 2018),
            DataSourceSample("Iphone SE", 2018),
            DataSourceSample("Iphone SE", 2018),
            DataSourceSample("Iphone SE", 2018),
            DataSourceSample("Iphone SE", 2018),
            DataSourceSample("Iphone SE", 2018),
        ).groupBy {
            // Split in 2 list, modulo and not
            it.modele.contains("Iphone")
        }.forEach { (isModulo, items) ->
            // For each mean for each list split
            // Here we have a map (key = isModulo) and each key have a list of it's items
            result.add(DataSourceHeaderSample("Is Iphone : $isModulo"))
            result.addAll(items)
            result.add(DataSourceFooterSample("Nombre d'items : ${items.count()}"))
            // Here we can add footer, just after our items
        }

        return result

    }

    fun onItemClick(objectDataSample: DataSourceSample, view : View){
        Toast.makeText(this, objectDataSample.modele, Toast.LENGTH_LONG).show()
    }

    fun stopActivity(view: View) {
        finish()
    }
}