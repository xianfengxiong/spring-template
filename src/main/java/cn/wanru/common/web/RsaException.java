package cn.wanru.common.web;

/**
 * @author xxf
 * @since 12/26/16
 */
public class RsaException extends RuntimeException {

    public RsaException() {
    }

    public RsaException(String message) {
        super(message);
    }

    public RsaException(Throwable cause) {
        super(cause);
    }

    public RsaException(String message, Throwable cause) {
        super(message, cause);
    }
}
