package com.artivisi.training.jpos.backend.aplikasibackend.dao;

import com.artivisi.training.jpos.backend.aplikasibackend.entity.Pembayaran;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PembayaranDao extends PagingAndSortingRepository<Pembayaran, String> {
    Pembayaran findByNasabahNoHandphone(String noHandphone);
}
