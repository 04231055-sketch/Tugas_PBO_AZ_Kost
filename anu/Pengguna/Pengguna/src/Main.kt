// 1. SUPERCLASS (Kelas Induk)
// Wajib menggunakan kata kunci 'open' agar bisa diwariskan ke kelas lain
open class Pengguna(
    val idPengguna: String,
    val nama: String,
    var noHp: String
) {
    // Method ini akan dimiliki juga oleh semua kelas anaknya
    fun tampilkanInfoDasar() {
        println("ID: $idPengguna | Nama: $nama | No. HP: $noHp")
    }
}

// 2. SUBCLASS (Kelas Anak)
// Kelas Penyewa mewarisi sifat dari Pengguna menggunakan tanda ':'
class Penyewa(
    idPengguna: String,
    nama: String,
    noHp: String,
    val nomorKamarSewa: String // Atribut khusus yang hanya dimiliki Penyewa
) : Pengguna(idPengguna, nama, noHp) { // <-- Ini adalah cara memanggil constructor induk (Super)

    fun tampilkanProfilDetail() {
        println("=== PROFIL PENYEWA AZ KOST ===")
        // Memanggil method dari kelas induk secara langsung
        tampilkanInfoDasar()
        println("Kamar Sewa : $nomorKamarSewa")
        println("==============================\n")
    }
}

// 3. SUBCLASS KEDUA (Opsional, untuk melengkapi hierarki)
class AdminKos(
    idPengguna: String,
    nama: String,
    noHp: String,
    val shiftJaga: String
) : Pengguna(idPengguna, nama, noHp) {

    fun tampilkanProfilAdmin() {
        println("=== PROFIL ADMIN AZ KOST ===")
        tampilkanInfoDasar()
        println("Shift Jaga : $shiftJaga")
        println("==============================\n")
    }
}

// ==========================================
// FUNGSI MAIN (Untuk Uji Coba Kodenya)
fun main() {
    println(">>> UJI COBA PEWARISAN (INHERITANCE) <<<\n")

    // Membuat objek dari kelas anak (Penyewa)
    val penyewa1 = Penyewa("PYW-001", "Muhammad Iskandar Yusuf", "081234567890", "Az-01")
    penyewa1.tampilkanProfilDetail()

    // Membuat objek dari kelas anak (AdminKos)
    val admin1 = AdminKos("ADM-001", "Bapak Kos", "089876543210", "Malam")
    admin1.tampilkanProfilAdmin()
}