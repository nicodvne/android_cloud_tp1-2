package com.example.ndav_androidapp.models

sealed class MyObjectForRecyclerView();

data class DataSourceSample(
    val modele: String,
    val annee: Int
): MyObjectForRecyclerView()

data class DataSourceHeaderSample(
    val header: String
): MyObjectForRecyclerView()

data class DataSourceFooterSample(
    val footer: String
): MyObjectForRecyclerView()