package com.example.restoranapp.data

data class MenuItem(
    val id: Int,
    val name: String,
    val price: String,
    val description: String,
    val category: String
)

val menuList = listOf(
    MenuItem(1, "Nasi Goreng Spesial", "Rp 35.000",
        "Nasi goreng dengan telur mata sapi, ayam suwir, dan sambal terasi khas rumahan.", "makanan"),
    MenuItem(2, "Ayam Bakar Madu", "Rp 45.000",
        "Ayam kampung bakar dengan bumbu madu dan rempah pilihan, disajikan dengan lalapan segar.", "makanan"),
    MenuItem(3, "Soto Betawi", "Rp 30.000",
        "Soto berkuah santan dengan daging sapi empuk, tomat, dan emping melinjo.", "makanan"),
    MenuItem(4, "Gado-Gado Jakarta", "Rp 25.000",
        "Sayuran rebus segar dengan saus kacang gurih, lontong, dan kerupuk udang.", "makanan"),
    MenuItem(5, "Rendang Padang", "Rp 50.000",
        "Daging sapi dimasak perlahan dengan santan dan rempah khas Minang selama 4 jam.", "makanan"),
    MenuItem(6, "Es Teh Manis", "Rp 8.000",
        "Teh hitam pilihan dengan gula aren, disajikan dengan es batu segar.", "minuman"),
    MenuItem(7, "Jus Alpukat", "Rp 18.000",
        "Alpukat segar diblender dengan susu kental manis dan sedikit cokelat cair.", "minuman"),
    MenuItem(8, "Cincau Hitam", "Rp 12.000",
        "Minuman segar cincau hitam dengan santan, gula merah, dan es serut.", "minuman"),
)