package com.oop.repository;

import org.json.simple.JSONObject;

public interface Repository {
    JSONObject getNhanVat();
    JSONObject getThoiKy();
    JSONObject getTranDanh();
}
