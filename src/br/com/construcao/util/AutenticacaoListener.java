package br.com.construcao.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.construcao.bean.AutenticacaoBean;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
	AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
	System.out.println("AutenticaçãoBean" + autenticacaoBean);
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
