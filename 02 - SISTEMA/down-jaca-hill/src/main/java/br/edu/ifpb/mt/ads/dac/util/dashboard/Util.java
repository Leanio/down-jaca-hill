package br.edu.ifpb.mt.ads.dac.util.dashboard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ifpb.mt.ads.dac.util.date.DateUtil;

public class Util {

	private static List<ResultadoDashboard> gerarLista(Date de, Date ate, int tipo, String formato) {
		List<ResultadoDashboard> lista = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat(formato);

		while (de.before(ate) || de.equals(ate)) {
			String dataFormatada = sdf.format(de);

			ResultadoDashboard rs = new ResultadoDashboard();
			rs.setGrupo(dataFormatada);
			rs.setTotal((Number) 0);

			de = DateUtil.somar(de, 1, tipo);

			lista.add(rs);
		}

		return lista;
	}

	public static List<String> gerarLabels(List<ResultadoDashboard> map) {
		List<String> labels = new ArrayList<>();

		for (ResultadoDashboard rd : map) {
			labels.add(rd.getGrupo());
		}

		return labels;
	}

	public static <T> List<T> juntarListasSemRepetir(List<T> a, List<T> b) {
		List<T> lista = new ArrayList<>();
		lista.addAll(a);

		for (T t : b) {
			if (!lista.contains(t)) {
				lista.add(t);
			}
		}

		return lista;
	}

	public static List<ResultadoDashboard> gerarListaDia(Date de, Date ate) {
		return gerarLista(de, ate, DateUtil.DIA, "yyyy-MM-dd");
	}

	public static List<ResultadoDashboard> gerarListaMes(Date de, Date ate) {
		return gerarLista(de, ate, DateUtil.MES, "M");
	}

	public static void preencherResultado(List<Object[]> resultado, List<ResultadoDashboard> map) {
		for (Object[] o : resultado) {
			String data = o[0].toString();
			Number total = (Number) o[1];

			for (ResultadoDashboard rs : map) {
				if (rs.getGrupo().equals(data)) {
					rs.setTotal(total);
					continue;
				}
			}
		}
	}

//	private static void preencher(List<Resultado> map, String grupo, Long total, int inicio, int fim) {
//		int meio = (inicio+fim)/2;
//		
//		Resultado rsMeio = map.get(meio);
//		String grupoMeio = rsMeio.getGrupo();
//		
//		if (grupoMeio.compareTo(grupo) == 0) {
//			rsMeio.setTotal(total);
//		} else if (grupoMeio.compareTo(grupo) == 1) {
//			preencher(map, grupo, total, inicio, meio-1);
//		} else {
//			preencher(map, grupo, total, meio+1, fim);
//		}
//		
//	}

}
