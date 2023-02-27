import CryptoJS from 'crypto-js';

const getKeyHex = (key: string) => CryptoJS.enc.Utf8.parse(key);
const option = {
  mode: CryptoJS.mode.ECB,
  padding: CryptoJS.pad.Pkcs7
}

// 加密
const encryptDES = (message: string, key='SYS980205') => {
  const encrypted = CryptoJS.DES.encrypt(message, getKeyHex(key), option);
  // return encrypted.ciphertext.toString(); // 返回hex格式密文
  return encrypted.toString(); // 返回base64格式密文
};

// 解密
const decryptDES = (message: string, key='SYS980205') => {
  const decrypted = CryptoJS.DES.decrypt(
    // { ciphertext: CryptoJS.enc.Hex.parse(message) }, // hex格式密文
    message, // base64格式密文
    getKeyHex(key),
    option,
  );
  return decrypted.toString(CryptoJS.enc.Utf8);
};

export {
  encryptDES,
  decryptDES
}
