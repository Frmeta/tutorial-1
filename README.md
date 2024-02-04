## Reflection 1

Clean code yang telah saya gunakan:
- meaningful names: menamakan function, file dengan nama yang jelas dan sesuai dengan tujuannya, contoh: 
    - `editProductPage()` pada ProductController.java, berupa menampilkan halaman html saat edit product
    - `findById()` pada ProductService.java, untuk mencari product berdasarkan id.
- Function: ukuran function kecil, melakukan satu hal, penamaan yang jelas,
- Comments: untuk memperjelas potongan kode

Secure coding practices yang telah saya gunakan:
- menggunakan method post untuk membuat product baru

Kesalahan yang pernah saya hadapi selama coding:
- Ketika mengedit product, ID product diset menjadi null pada saat tombol Submit ditekan

    Solusi: menambahkan input field baru untuk menyimpan ID di halaman editProduct yang bersifat readonly

- Algoritma mencari product yang diletakkan di layer Service membuat test tidak bisa dijalankan. 

    Solusi: meletakkan algoritma tersebut di layer Repository.

Cara memperbaiki kode:
- mempelajari open source project
- membuat sistem autentikasi
- menghubungkan repository Github dengan auto deploy

## Reflection 2

1.
   - Setelah menulis unit test, saya merasa kode saya menjadi lebih terpercaya saat dijalankan.
   - Tidak ada angka pasti untuk menentukan banyaknya unit tests yang digunakan di suatu class, namun ahli setuju bahwa target yang bagus adalah 80% code coverage.
   - Agar unit test dipastikan cukup untuk memastikan program kita, kita harus melakukan test terhadap keseluruhan fitur program agar tidak ada kasus yang tidak di-handle.
   - Code coverage 100% tidak memastikan bahwa kode kita tidak memiliki bug atau error sama sekali, namun masih bisa menyesatkan.

2. Menurut saya kode tersebut menjadi kurang bersih apabila membuat class Java baru hanya untuk memeriksa banyak item, sebab functional test untuk memeriksa nama produk & banyak produk tidak jauh berbeda. Akibatnya akan ada banyak kode yang sama tapi diulang berkali-kali, dan adanya suatu perubahan sistem (misalnya template html berubah) membuat programmer harus mengubah kedua class tersebut satu persatu. Solusinya dapat menggabungkan kedua class tersebut menjadi satu dan memindahkan potongan kode yang sama (misalnya  simulasi create product) ke dalam suatu method yang dapat dipanggil oleh kedua test tersebut (misalnya setup).


