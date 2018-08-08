package dev.profissional.kosmo.com.br.guiaproprofissional.utils;


import dev.profissional.kosmo.com.br.guiaproprofissional.interfaces.FragmentInterface;
import dev.profissional.kosmo.com.br.guiaproprofissional.model.Atendimento;
import dev.profissional.kosmo.com.br.guiaproprofissional.model.Autenticacao;
import dev.profissional.kosmo.com.br.guiaproprofissional.model.Profissional;

/**
 * Created by Filipe on 11/03/2018.
 */

public class VariaveisEstaticas {

    private static FragmentInterface fragmentInterface;
    private static Profissional profissional;
    private static Atendimento atendimento;
    private static Autenticacao autenticacao;

    public static Profissional getProfissional() {
        return profissional;
    }

    public static void setProfissional(Profissional profissional) {
        VariaveisEstaticas.profissional = profissional;
    }

    public static FragmentInterface getFragmentInterface() {
        return fragmentInterface;
    }

    public static void setFragmentInterface(FragmentInterface fragmentInterface) {
        VariaveisEstaticas.fragmentInterface = fragmentInterface;
    }

    public static Atendimento getAtendimento() {
        return atendimento;
    }

    public static void setAtendimento(Atendimento atendimento) {
        VariaveisEstaticas.atendimento = atendimento;
    }

    public static Autenticacao getAutenticacao() {
        return autenticacao;
    }

    public static void setAutenticacao(Autenticacao autenticacao) {
        VariaveisEstaticas.autenticacao = autenticacao;
    }
}
