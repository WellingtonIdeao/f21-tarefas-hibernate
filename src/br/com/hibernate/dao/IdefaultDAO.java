package br.com.hibernate.dao;

import java.util.List;

public interface IdefaultDAO<T> {
	void adiciona(T t);
	List<T> lista();
	void altera(T t);
	T buscaPorId(Long id);
	void remove(Long id);
	void finaliza(Long id);
	boolean existe(T t);
}
