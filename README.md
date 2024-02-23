Link deploy Koyeb: https://tutorial-1-redorganization.koyeb.app

## Module 1

**Reflection 1**

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

**Reflection 2**

1.
   - Setelah menulis unit test, saya merasa kode saya menjadi lebih terpercaya saat dijalankan.
   - Tidak ada angka pasti untuk menentukan banyaknya unit tests yang digunakan di suatu class, namun ahli setuju bahwa target yang bagus adalah 80% code coverage.
   - Agar unit test dipastikan cukup untuk memastikan program kita, kita harus melakukan test terhadap keseluruhan fitur program agar tidak ada kasus yang tidak di-handle.
   - Code coverage 100% tidak memastikan bahwa kode kita tidak memiliki bug atau error sama sekali, namun masih bisa menyesatkan.

2. Menurut saya kode tersebut menjadi kurang bersih apabila membuat class Java baru hanya untuk memeriksa banyak item, sebab functional test untuk memeriksa nama produk & banyak produk tidak jauh berbeda. Akibatnya akan ada banyak kode yang sama tapi diulang berkali-kali, dan adanya suatu perubahan sistem (misalnya template html berubah) membuat programmer harus mengubah kedua class tersebut satu persatu. Solusinya dapat menggabungkan kedua class tersebut menjadi satu dan memindahkan potongan kode yang sama (misalnya  simulasi create product) ke dalam suatu method yang dapat dipanggil oleh kedua test tersebut (misalnya setup).


## Module 2


1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

    - Penggunaan tanda kurung yang berlebihan. Solusinya: menghilangkannya.

    - Access modifer dari variabel dan function pada interface `ProductService` yang tidak perlu `public`. Solusinya: menghilangkan kata public.

    - `System.out.println(..)` yang hanya digunakan sebagai debugging. Solusinya: menghilangkannya.

    - Access modifier dari semua class yang berhubungan dengan test tidak perlu public. Solusinya: mengubah public menjadi default.

    - Adanya import yang tidak dipakai. Solusi: menghapus import yang tidak dipakai.

    - Method *naming convention* yang salah. Solusi: mengubah semua menjadi CamelCase.

    - Adanya *local variable* yang tidak dipakai. Solusi: menghilangkan variabel tersebut.
    


2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

    - Continuous Integration terdiri dari fase Code dan Test. Sedangkan Continuous Delivery/Deployment terdiri dari fase Review dan Operational. Menurut saya implementasi sekarang sudah menerapkan CI/CD tersebut. Sebab kode dalam `ci.yml` sudah dapat melakukan proses test secara otomatis ketika kita melakukan push atau pull request ke repository Github tersebut. Selain itu penggunaan `Koyeb` sudah menerapkan Continuous Deployment karena web app akan otomatis terdeploy seketika ada push/pull request pada repository Github tersebut.


## Module 3
SOLID Principles:
1. SRP (Single Responsibility Principle)
   
   SRP adalah aturan dimana memisahkan file berdasarnkan tanggung jawabnya. Kode saya sudah menerapkan SRP, dimana CarController dan ProductController (yang awalnya digabung di satu file) dipisah menjadi dua file berbeda agar tiap tanggung jawab dienkapsulasi ke dalam satu file. Saya menghilangkan hubungan inheritance antara CarController dan ProductController sebab CarController tidak diharapkan mengatur mapping untuk membuat product. Selain itu saya juga menambahkan HomeController, yakni controller untuk mengatur home page, di file yang berbeda

2. OCP (Open-Closed Principle)

   OCP adalah aturan dimana apapun komponen pada kode kita harus open/terbuka untuk pengembangan atau ekstensi namun closed/tertutup pada modifikasi. Kode saya sudah menerapkan prinsip ini yaitu pada bagian controller. Apabila kita ingin menambahkan mapping link yang baru, dapat membuat file controller baru tanpa mengupdate file yang lama.

3. LSP (Liskov Subtitution Principle)
   
    LSP adalah aturan dimana apabila suatu subclass inherit dari sebuah superclass, maka diharapkan subclass tersebut memiliki behaviour yang serupa dengan superclassnya. Kode saya sudah menerapkan LSP, contohnya CarServiceImpl dan ProductServiceImpl yang telah mengimplemen interface CarService dan ProductService dengan meng-override methodnya dengan sesuai tanpa meggantikan fungsionalitas dari interface tersebut.

4. ISP (Interface Segregation Principle)
   
   ISP adalah aturan dimana interface yang berukuran besar sebaiknya dipisah menjadi beberapa bagian kecil yang spesifik. Kode saya sudah menerapkan ISP pada bagian CarServiceImpl dan ProductServiceImpl dimana kedua interface tersebut tidak digabung karena mereka memiliki behaviour yang spesifik untuk car service atau product srevice.

5. DIP (Dependency Inversion Principle)
   
   DIP adalah aturan dimana suatu class sebaiknya memiliki ketergantungan terhadap interface atau class abstract daripada class concrete. Hal ini berguna untuk meningkatkan fleksibilitas. Kode saya sudah menerapkan prinsip ini, contohnya pada ProductController dan CarController yang menyimpan referensi service dalam bentuk interface `ProductService` dan `CarService`, dan bukan concrete classnya.


Keuntungan menerapkan prinsip SOLID
- Membantu kode kita menjadi mudah dipahami orang lain
- Membuat kode kita muda dikelola (scalability) dan fleksibel
- Meningkatkan kualitas software
- Memudahkan mencari penyebab error atau bug

Kerugian tidak menerapkan prinsip SOLID
- Kode hanya dipahami oleh orang yang menulis kode, orang lain yang membaca kode lebih banyak menghabiskan waktunya dalam memahami kode lama dibanding membuat kode baru
- Kode sulit dikelola, sulit menambah fitur, dan tidak fleksibel
- Apabila menemukan error atau bug akan sulit melacak kode penyebabnya