// File: DataModels.kt

// 1. Class Pertama: KamarKos (Menggunakan custom setter untuk validasi harga)
class KamarKos(
    val nomorKamar: String,
    hargaAwal: Int,
    isTersediaAwal: Boolean = true
) {
    // Menerapkan private set: Status hanya bisa dibaca dari luar, tapi wajib diubah pakai fungsi
    var isTersedia: Boolean = isTersediaAwal
        private set

    // Menerapkan Custom Setter: Harga tidak boleh bernilai negatif (minus)
    var harga: Int = hargaAwal
        set(value) {
            if (value >= 0) {
                field = value
            } else {
                println("!! [Sistem Ditolak]: Harga kamar tidak boleh minus. Nilai dikembalikan ke awal.")
            }
        }

    fun tampilkanInfo() {
        val status = if (isTersedia) "Kosong" else "X Terisi"
        println(" Kamar: $nomorKamar | Harga: Rp${"%,d".format(harga)} | Status: $status")
    }

    fun ubahStatusKamar() {
        isTersedia = !isTersedia
    }
}

// 2. Class Kedua: Penyewa (Menggunakan custom setter untuk validasi Nomor HP)
class Penyewa(
    val idPenyewa: String,
    val nama: String,
    noHpAwal: String
) {
    // Menerapkan Custom Setter: No HP harus minimal 10 digit dan hanya berisi angka
    var noHp: String = noHpAwal
        set(value) {
            if (value.length >= 10 && value.all { it.isDigit() }) {
                field = value
            } else {
                println("!! [Sistem Ditolak]: Nomor HP tidak valid. Harus berupa angka & minimal 10 digit.")
            }
        }

    fun tampilkanProfil() {
        println(" Penyewa: $nama | No. HP: $noHp")
    }
}

// ==========================================
// FUNGSI MAIN (Hanya untuk menguji apakah validasi keamanannya berfungsi)
fun main() {
    println("=== UJI COBA KEAMANAN ENKAPSULASI AZ KOST ===\n")

    // UJI COBA 1: Class KamarKos
    val kamar1 = KamarKos("Az-01", 1500000)
    kamar1.tampilkanInfo()

    println("\n>> Mencoba mengubah harga kamar menjadi -500.000 (Minus)...")
    kamar1.harga = -500000 // Akan memicu pesan error dari Custom Setter
    kamar1.tampilkanInfo() // Harga akan tetap 1.500.000 karena tertahan validasi


    // UJI COBA 2: Class Penyewa
    println("\n=== DATA PENYEWA ===")
    val penyewa1 = Penyewa("P-01", "Muhammad Iskandar Yusuf", "081234567890")
    penyewa1.tampilkanProfil()

    println("\n>> Mencoba mengubah No HP menjadi teks sembarangan 'NomorBaru'...")
    penyewa1.noHp = "NomorBaru" // Akan memicu pesan error dari Custom Setter
    penyewa1.tampilkanProfil() // Nomor HP akan tetap 081234567890
}