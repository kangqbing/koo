package com.bayern.factory;

import java.security.NoSuchAlgorithmException;

import com.bayern.other.SecureType;
import com.bayern.secure.AESCodec;
import com.bayern.secure.BasicCodec;
import com.bayern.secure.DESCodec;
import com.bayern.secure.MD5Codec;
import com.bayern.secure.NoSecureCodec;
import com.bayern.secure.RSAForPrivateCodec;
import com.bayern.secure.RSAForPublicCodec;
import com.bayern.secure.SHACodec;

public class SecureFactory
{

	public static BasicCodec getCodec(SecureType type, String key) throws NoSuchAlgorithmException
	{
		BasicCodec codec = null;
		switch(type)
		{
			case MD5:
				codec = new MD5Codec();
				break;
			case SHA:
				codec = new SHACodec();
				break;
			case DES:
				if(key != null && !"".equals(key))
				{
					codec = new DESCodec(key);
				}
				else
				{
					codec = new DESCodec();
				}
				break;
			case AES:
				if(key != null && !"".equals(key))
				{
					codec = new AESCodec(key);
				}
				else
				{
					codec = new AESCodec();
				}
				break;
			case RSA_PRIVATE:
				codec = new RSAForPrivateCodec();
				break;
			case RSA_PUBLIC:
				codec = new RSAForPublicCodec(key);
				break;
			default :
				codec = new NoSecureCodec();
		}
		return codec;
	}
}
