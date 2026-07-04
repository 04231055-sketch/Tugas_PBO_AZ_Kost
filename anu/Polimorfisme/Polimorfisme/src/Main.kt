// ==========================================
// 1. OVERRIDING (Beda Kelas, Induk vs Anak)
// ==========================================
open class KamarKos(
    val nomorKamar: String,
    val hargaDasar: Int
) {
    // Kata kunci 'open' wajib ditambahkan agar method ini bisa di-override oleh kelas anak
    open fun hitungSewa(lamaBulan: Int): Int {
        return hargaDasar * lamaBulan
    }

    open fun tampilkanInfo() {
        println("Kamar: $nomorKamar | Tipe: Standar | Harga: Rp${"%,d".format(hargaDasar)}/bulan")
    }
}

// Kelas Anak (KamarVIP) mewarisi KamarKos
class KamarVIP(
    nomorKamar: String,
    hargaDasar: Int,
    val fasilitasEkstra: String
) : KamarKos(nomorKamar, hargaDasar) {

    // OVERRIDING: Menimpa cara menghitung sewa khusus kamar VIP (ada tambahan biaya layanan)
    override fun hitungSewa(lamaBulan: Int): Int {
        val biayaLayanan = 200000
        return (hargaDasar * lamaBulan) + biayaLayanan
    }

    // OVERRIDING: Menimpa cara menampilkan info agar fasilitas eksklusifnya terlihat
    override fun tampilkanInfo() {
        println("Kamar: $nomorKamar | Tipe: VIP | Fasilitas: $fasilitasEkstra | Harga: Rp${"%,d".format(hargaDasar)}/bulan")
    }
}

// ==========================================
// 2. OVERLOADING (Satu Kelas, Nama Method Sama, Tipe Parameter Beda)
// ==========================================
class SistemPencarianKost {
    private val daftarKamar = mutableListOf<KamarKos>()

    fun tambahKamar(kamar: KamarKos) {
        daftarKamar.add(kamar)
    }

    // OVERLOADING 1: Mencari berdasarkan Nomor Kamar (Parameter Teks / String)
    fun cariKamar(nomorKamar: String) {
        println("\n>> Mencari kamar dengan nomor: $nomorKamar")
        val hasil = daftarKamar.find { it.nomorKamar.equals(nomorKamar, ignoreCase = true) }
        if (hasil != null) hasil.tampilkanInfo() else println("Kamar tidak ditemukan.")
    }

    // OVERLOADING 2: Mencari berdasarkan Harga Maksimal (Parameter Angka / Int)
    fun cariKamar(hargaMaks: Int) {
        println("\n>> Mencari kamar dengan harga maksimal: Rp${"%,d".format(hargaMaks)}")
        val hasil = daftarKamar.filter { it.hargaDasar <= hargaMaks }
        if (hasil.isNotEmpty()) {
            hasil.forEach { it.tampilkanInfo() }
        } else {
            println("Tidak ada kamar dalam rentang harga tersebut.")
        }
    }
}

// ==========================================
// FUNGSI MAIN (Uji Coba Sistem)
// ==========================================
fun main() {
    println("=== UJI COBA POLIMORFISME AZ KOST ===\n")

    val sistem = SistemPencarianKost()

    // Memasukkan data kamar
    val kamarStandar = KamarKos("Az-01", 1000000)
    val kamarVIP = KamarVIP("Az-05", 1500000, "AC, TV, Kulkas")
    sistem.tambahKamar(kamarStandar)
    sistem.tambahKamar(kamarVIP)

    // A. MENGUJI OVERRIDING (Fungsi hitungSewa yang dipanggil sama, tapi hasil/rumusnya beda)
    println("Total Sewa 2 Bulan (Az-01 Standar) : Rp${"%,d".format(kamarStandar.hitungSewa(2))}")
    println("Total Sewa 2 Bulan (Az-05 VIP)     : Rp${"%,d".format(kamarVIP.hitungSewa(2))} (Termasuk Biaya Layanan)")
    println("------------------------------------------")

    // B. MENGUJI OVERLOADING (Memanggil nama fungsi yang sama persis: 'cariKamar')
    sistem.cariKamar("az-05")   // Sistem otomatis memilih Overloading 1 karena inputnya teks
    sistem.cariKamar(1200000) // Sistem otomatis memilih Overloading 2 karena inputnya angka
}