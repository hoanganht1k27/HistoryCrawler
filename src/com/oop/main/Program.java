package com.oop.main;

import com.oop.repository.Repository;
import com.oop.repository.impl.*;

public class Program {
    private Repository[] repositories = {
            ThoiKyRepositoryImpl.getInstance(),
            NhanVatRepositoryImpl.getInstance(),
            DiaDanhRepositoryImpl.getInstance(),
            DanhNhanRepositoryImpl.getInstance(),
            SuKienRepositoryImpl.getInstance()
    };

    public Program() {
        for (Repository repo : repositories) {
            repo.loadData();
        }
    }
}
