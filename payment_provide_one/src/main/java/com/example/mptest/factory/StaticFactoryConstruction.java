package com.example.mptest.factory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.bouncycastle.util.encoders.BufferedEncoder;

import java.nio.file.Files;

public class StaticFactoryConstruction {
    public static void main(String[] args) {
        QueryWrapper queryWrapper = Wrappers.query();
        Boolean.valueOf(true);
    }
}
