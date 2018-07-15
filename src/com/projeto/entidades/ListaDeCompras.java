package com.projeto.entidades;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import com.projeto.ordenacao.OrdenaItensPorNome;
import com.projeto.ordenacao.OrdenarPorCategoria;

/**
 * Classe reposavel por uma lista de compras.
 * 
 * @author  Igor Silveira
 * 			Jose Davi
 * 			Matheus Gusmao
 * 			Rich Ramalho
 *
 */
public class ListaDeCompras {
	/**
	 * Atributo do tipo Lista.
	 */
	private List<Item> itens;
	/**
	 * Atributo do timpo TreeMap, passando Item e valores Double.
	 */
	private TreeMap<Item, Double> qntdItens;
	/**
	 * Atributo do tipo String referente a descricao de uma lista de compras.
	 */
	private String descritor;
	/**
	 * Atributo do tipo Date, referente a data da lista de compras.
	 */
	private Date data;
	/**
	 * Atributo do tipo Double referente ao Valor total da lista de compras.
	 */
	private double valorTotal;
	/**
	 * Atributo do tipo String referente ao Local de compra.
	 */
	private String localDeCompra;
	
	/**
	 * Construtor de uma Lista de compras.
	 * 
	 * @param descritor do tipo String, que e referente a descricao de uma lista de compras.
	 */
	public ListaDeCompras(String descritor) {
		this.descritor = descritor;
		this.itens = new ArrayList<>();
		this.qntdItens = new TreeMap<>();
		this.data = new Date();
	}
	
	/**
	 * Metodo responsavel por adicionr uma compra a lista de compras.
	 * 
	 * @param qntd do tipo Double, referente a quantidade de determinada compra.
	 * @param item referente ao Item que esta sendo adquirido.
	 * @return o tamanho da lista itens, apos adicionar a o item comprado.
	 */
	public int adicionaCompraALista(double qntd, Item item) {
		this.qntdItens.put(item, qntd);
		this.itens.add(item);
		
		return this.itens.size();
	}
	
	/**
	 * Metodo responsavel por pesquisar determinado item comprado em uma lista de compras.
	 * 
	 * @param item referente ao Item que foi comprado.
	 * @return a representacao textual do item comprado que sera pesquisado.
	 */
	public String pesquisaCompraEmLista(Item item) {
		return item.getToStringEmLista(this.qntdItens.get(item));
	}
	
	/**
	 * Metodo que atualiza uma lista de compra com os itens comprados.
	 * 
	 * @param item referente ao item que devera ser atualizado na lista.
	 * @param novaquantidade do tipo Double referente a nova quantidade daquele item, que_
	 * devera ser atualizado.
	 */
	public void atualizaCompraDeLista(Item item, double novaquantidade) {
		if (this.qntdItens.containsKey(item)) {
			this.qntdItens.replace(item, novaquantidade);
		}
	}
	
	/**
	 * Metodo que retorna a descricao textual de uma lista de compras.
	 */
	@Override
	public String toString() {
		return this.descritor;
	}

	/**
	 * Metodo referente a finalizar uma lista de compras.
	 * 
	 * @param localDeCompra do tipo String referente ao local de compra da lista.
	 * @param valorFinal do tipo Double, referente ao valor final da lista em questao.
	 */
	public void finalizarListaDeCompras(String localDeCompra, double valorFinal) {
		this.localDeCompra = localDeCompra;
		this.valorTotal = valorFinal;
	}
	
	/**
	 * Metodo responsavel por emitir a representacao textual do item na lista.
	 * 
	 * @param id do tipo Inteiro, referente a identificacao do item.
	 * @return a representacao textual do item em determinada lista de compras.
	 */
	public String getItemLista(int id) {
		if (this.itens.size() <= id) {
			return "";
		}
		this.ordenarPorNomeCategoria();
		Item i = this.itens.get(id);
		double quant = this.qntdItens.get(i);
		return i.getToStringEmLista(quant);
	}
	
	/**
	 * Metodo responsavel por ordenar os itens pelo nome de suas categorias.
	 * Metodo sem retorno.
	 */
	private void ordenarPorNomeCategoria() {
		Collections.sort(this.itens, new OrdenaItensPorNome());
		Collections.sort(this.itens, new OrdenarPorCategoria());
	}

	/**
	 * Metodo que deleta uma compra de uma lista de compras.
	 * 
	 * @param i referente ao item a ser removido da lista.
	 */
	public void deletaCompraDeLista(Item i) {
		this.itens.remove(i);
		this.qntdItens.remove(i);
	}
}