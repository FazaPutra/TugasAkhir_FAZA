package faza111;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Tugas_Akhir_Faza {
static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
static int pilihan;
static int jumlahPengunjung = 0;
static char ulang;
static String[] nama = new String[100];
static String[] noHP = new String[100];
static String[][] layanan = new String[100][10];
static int[] klayanan = new int[10];
static double[][] hrg = new double[100][10];
static double[] subThrg = new double[100];
static double[] subThrg1 = new double[100];
static double[] gThrg = new double[100];
static double[] gThrg1 = new double[100];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        System.out.println("PROGRAM BENGKEL MOTOR");
        System.out.println("24.240.0031 | M. Athfalul Faza Wiba Putra");
        System.out.println();
        menu();
    
    }
    public static void menu() throws IOException {
        // Menampilkan menu utama
        System.out.println("|=========================|");
        System.out.println("|     PROGRAM BENGKEL     |");
        System.out.println("|    ERA MOTOR MANDIRI    |");
        System.out.println("|  JL. dr. Sutomo, Batang |");
        System.out.println("|=========================|");
        System.out.println();
        System.out.print("===> MENU UTAMA <===\n" +
"=[1] Isi Data\n" +
"=[2] Lihat Data\n" +
"=[3] Koreksi / Edit Data\n" +
"=[4] Hapus Data\n" +
"=[5] Keluar\n\n" + 
"==> Pilih Menu > ");
        try {
                    pilihan = Integer.parseInt(input.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Pilihan layanan tidak valid. Harap masukkan angka.");
                    menu();
                    return;
                }
        
        System.out.println();
    
switch (pilihan) {
            case 1:
                // Memanggil fungsi isiData untuk memasukkan data pengunjung
                isiData();
                break;
            case 2:
                // Memanggil fungsi lihatLaporan untuk menampilkan laporan data pengunjung
                lihatData();
                break;
            case 3:
                // Memanggil fungsi editData untuk mengoreksi atau mengedit data pengunjung
                editData();
                break;
            case 4:
                // Memanggil fungsi hapusData untuk menghapus data pengunjung
                hapusData();
                break;
            case 5:
                // Menampilkan pesan selesai dan menutup program
                System.out.println("Terimakasih sudah menggunakan program ini :)");
                System.exit(0);
                break;
            default:
                // Menampilkan pesan jika pilihan menu tidak valid
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                System.out.println();

                // Memanggil fungsi menu kembali
                menu();
                break;
        }
    }
     public static void isiData() throws IOException {
        System.out.println("ISI DATA PENGUNJUNG");
        System.out.println();
        do {
            System.out.print("Masukkan nama pengunjung\t: ");
            nama[jumlahPengunjung] = input.readLine();
            System.out.print("Masukkan nomor HP pengunjung\t: ");
                noHP[jumlahPengunjung] = input.readLine();
           for (int i = 0; i < 10; i++) {
                layanan[jumlahPengunjung][i] = null;
                hrg[jumlahPengunjung][i] = 0;
            }
            int jumlahLayanan = 0;
            do {
                System.out.println("Pilih Layanan Jasa Bengkel: ");
                System.out.println("1\tGanti Oli\t67.000");
                System.out.println("2\tGanti Aki\t210.000");
                System.out.println("3\tGanti Piston\t155.000");
                System.out.println("4\tGanti Ban\t100.000");
                System.out.print("Masukkan pilihan layanan (1-4)\t: ");
                try {
                    klayanan[jumlahLayanan] = Integer.parseInt(input.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Pilihan layanan tidak valid. Harap masukkan angka.");
                    menu();
                    return;
                }
                if (klayanan[jumlahLayanan] >= 1 && klayanan[jumlahLayanan] <= 4) {
                    layanan[jumlahPengunjung][jumlahLayanan] = getServiceName(klayanan[jumlahLayanan]);
                    hrg[jumlahPengunjung][jumlahLayanan] = getServicePrice(klayanan[jumlahLayanan]);
                    jumlahLayanan++;
                    System.out.print("Tambah layanan lagi [Y/T]: ");
                    ulang = input.readLine().charAt(0);
                } else {
                    System.out.println("Pilihan layanan tidak valid. Silakan coba lagi.");
                }
            } while ((ulang == 'Y' || ulang == 'y') && jumlahLayanan < 10);
            System.out.println();
            jumlahPengunjung++;
            System.out.print("Isi data lagi [Y/T]: ");
            ulang = input.readLine().charAt(0);
            System.out.println();
        } while (ulang == 'Y' || ulang == 'y');
        menu();
    }
    public static void lihatData() throws IOException {
    if (jumlahPengunjung == 0) {
        System.out.println("Belum ada data pengunjung yang tersimpan.");
        System.out.println();
    } else {
        int dataPerHalaman = 3;
        int halaman = 1;
        for (int i = 0; i < jumlahPengunjung; i += dataPerHalaman) {
            System.out.println("LIHAT DATA PENGUNJUNG");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %15s | %-118s |\n","No Halaman", halaman);
            System.out.println("|------------------------------------------------------------------------------------------------------------------------------------------|");
            System.out.printf("| %-4s | %-20s | %-15s | %-70s | %-15s |\n", "NO", "NAMA PENGUNJUNG", "NOMER HP", "LAYANAN", "BIAYA LAYANAN");
            System.out.println("|------------------------------------------------------------------------------------------------------------------------------------------|");
            double subtotalHalaman = 0;
            for (int j = 0; j < dataPerHalaman && i + j < jumlahPengunjung; j++) {
                int nomor = i + j;
                System.out.printf("| %-4s | %-20s | %-15s | %-70s | %-15s |\n", nomor + 1, nama[nomor], noHP[nomor], concatenateServices(nomor), calculateTotalServicePrice(nomor));
                double subtotal = calculateTotalServicePrice(nomor);
                gThrg[nomor] = subtotal;
                subtotalHalaman += subtotal;
            }
            System.out.println("|------------------------------------------------------------------------------------------------------------------------------------------|");
            System.out.printf("| %118s | %-15s |\n","Subtotal Halaman", subtotalHalaman);
            System.out.println("|------------------------------------------------------------------------------------------------------------------------------------------|");
            if (i + dataPerHalaman >= jumlahPengunjung) {
                double grandTotal = 0;
                for (int k = 0; k < jumlahPengunjung; k++) {
                    grandTotal += gThrg[k];
                }
                System.out.printf("| %118s |%-17s|\n", "Grand Total", grandTotal);
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Ini adalah halaman terakhir.");
            }
            System.out.println();
            System.out.print("Tekan ENTER untuk melanjutkan...");
            input.readLine();
            System.out.println();
            halaman++;
        }
    }
    menu();
}
    public static void editData() throws IOException {
        System.out.println("EDIT DATA");
        System.out.println();
        if (jumlahPengunjung == 0) {
            System.out.println("Belum ada data pengunjung yang tersimpan.");
            System.out.println();
        } else {
            System.out.println("Data pengunjung yang tersimpan:");
            System.out.println();

            for (int i = 0; i < jumlahPengunjung; i++) {
                System.out.println((i + 1) + ". " + nama[i]);
            }
            System.out.println();

            System.out.print("Masukkan nomor data pengunjung yang ingin diedit (1-" + jumlahPengunjung + "): ");
            int nomor = Integer.parseInt(input.readLine());
            System.out.println();

            if (nomor < 1 || nomor > jumlahPengunjung) {
                System.out.println("Nomor data pengunjung tidak valid. Silakan coba lagi.");
                System.out.println();
            } else {
                System.out.println("Data pengunjung yang dipilih:");
                System.out.println();
                System.out.println("Nama pengunjung: " + nama[nomor - 1]);
                System.out.println("Nomor HP pengunjung: " + noHP[nomor - 1]);
                System.out.println("Jenis Layanan pengunjung: " + concatenateServices(nomor - 1));
                System.out.println("Biaya Layanan pengunjung: " + calculateTotalServicePrice(nomor - 1));
                System.out.println();

                System.out.print("Masukkan nama pengunjung yang baru: ");
                nama[nomor - 1] = input.readLine();

                System.out.print("Masukkan nomor HP pengunjung yang baru: ");
                
                    noHP[nomor - 1] = input.readLine();
                   

                for (int i = 0; i < 10; i++) {
                    layanan[nomor - 1][i] = null;
                    hrg[nomor - 1][i] = 0;
                }

                int jumlahLayanan = 0;

                do {
                    System.out.println("Pilih Layanan Jasa Bengkel: ");
                    System.out.println("1\tGanti Oli\t67.000");
                    System.out.println("2\tGanti Aki\t210.000");
                    System.out.println("3\tGanti Piston\t155.000");
                    System.out.println("4\tGanti Ban\t100.000");

                    System.out.print("Masukkan pilihan layanan yang baru (1-4): ");
                    try {
                        klayanan[jumlahLayanan] = Integer.parseInt(input.readLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Pilihan layanan tidak valid. Harap masukkan angka.");
                        menu();
                        return;
                    }

                    if (klayanan[jumlahLayanan] >= 1 && klayanan[jumlahLayanan] <= 4) {
                        layanan[nomor - 1][jumlahLayanan] = getServiceName(klayanan[jumlahLayanan]);
                        hrg[nomor - 1][jumlahLayanan] = getServicePrice(klayanan[jumlahLayanan]);

                        jumlahLayanan++;

                        System.out.print("Tambah layanan lagi [Y/T]: ");
                        ulang = input.readLine().charAt(0);
                    } else {
                        System.out.println("Pilihan layanan tidak valid. Silakan coba lagi.");
                    }

                } while ((ulang == 'Y' || ulang == 'y') && jumlahLayanan < 10);

                System.out.println("Data pengunjung berhasil diedit.");
                System.out.println();
            }
        }
        menu();
    }
    public static void hapusData() throws IOException {
        System.out.println("HAPUS DATA");
        System.out.println();

        if (jumlahPengunjung == 0) {
            System.out.println("Belum ada data pengunjung yang tersimpan.");
            System.out.println();
        } else {
            System.out.println("Data pengunjung yang tersimpan:");
            System.out.println();

            for (int i = 0; i < jumlahPengunjung; i++) {
                System.out.println((i + 1) + ". " + nama[i]);
            }
            System.out.println();

            System.out.print("Masukkan nomor data pengun[]jung yang ingin dihapus (1-" + jumlahPengunjung + "): ");
            int nomor = Integer.parseInt(input.readLine());
            System.out.println();

            if (nomor < 1 || nomor > jumlahPengunjung) {
                System.out.println("Nomor data pengunjung tidak valid. Silakan coba lagi.");
                System.out.println();
            } else {
                for (int i = nomor - 1; i < jumlahPengunjung - 1; i++) {
                    nama[i] = nama[i + 1];
                    noHP[i] = noHP[i + 1];
                    for (int j = 0; j < 10; j++) {
                        layanan[i][j] = layanan[i + 1][j];
                        hrg[i][j] = hrg[i + 1][j];
                    }
                }

                jumlahPengunjung--;

                System.out.println("Data pengunjung berhasil dihapus.");
                System.out.println();
            }
        }
        menu();
    }

    private static String concatenateServices(int nomor) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (layanan[nomor][i] != null) {
                result.append(layanan[nomor][i]).append(", ");
            }
        }
        if (result.length() > 0) {
            result.delete(result.length() - 2, result.length());
        }
        return result.toString();
    }

    private static double calculateTotalServicePrice(int nomor) {
    double total = 0;
    for (int i = 0; i < 10; i++) {
        total += hrg[nomor][i];
    }
    return total;
}

    private static String getServiceName(int klayanan) {
        switch (klayanan) {
            case 1:
                return "Ganti Oli";
            case 2:
                return "Ganti Aki";
            case 3:
                return "Ganti Piston";
            case 4:
                return "Ganti Ban";
            default:
                return "";
        }
    }

    private static double getServicePrice(int klayanan) {
        switch (klayanan) {
            case 1: 
                return 67000;
            case 2:
                return 210000;
            case 3:
                return 155000;
            case 4:
                return 100000;
            default:
                return 0;
        }
    }
}
