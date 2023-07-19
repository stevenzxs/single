package com.steven.common.utils.crypto.cipher.sm4;

import com.steven.common.utils.crypto.cipher.SM4Cipher;

public class SM4_Context {
    public int mode;

    public long[] sk;

    public boolean isPadding;

    public int paddingMode;

    public boolean isHexStringKey;

    public SM4Cipher.Type convertMode;

    public SM4_Context() {
        this.mode = SM4.SM4_ENCRYPT;
        this.isPadding = true;
        this.sk = new long[32];
        this.paddingMode = SM4.PADDINGLENGTH;
        this.isHexStringKey = false;
        this.convertMode = SM4Cipher.Type.BASE64;;
    }
}
