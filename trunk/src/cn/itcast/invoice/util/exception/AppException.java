package cn.itcast.invoice.util.exception;

public class AppException extends Exception{
	public AppException(){
	}
	public AppException(String msg){
		super(msg);
	}
	public AppException(String msg,Throwable t){
		super(msg,t);
	}
	public AppException(Throwable t){
		super(t);
	}
}
