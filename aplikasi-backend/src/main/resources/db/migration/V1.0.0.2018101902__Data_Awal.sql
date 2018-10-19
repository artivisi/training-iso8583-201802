insert into nasabah (id, no_handphone, nama)
values ('n001', '081234567890', 'Nasabah 001');

insert into pembayaran (id, id_nasabah, waktu_pembayaran, nilai, fee, referensi)
values ('p001', 'n001', '2018-10-01 08:08:08', 120000.00, 2000.00, '123456789012');
