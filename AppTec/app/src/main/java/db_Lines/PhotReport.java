package db_Lines;

import java.util.ArrayList;

/**
 * Created by Rodolfo on 29/05/2015.
 */
public class PhotReport {
    String mNomeEmpresa,
            mNumPat, mNumOrdemManutencao; // I didn't created an int or double variable for this because I'm not 100% sure that it'll always be numbers

    ArrayList<String> mPathToPhotos;

    public PhotReport(String mNomeEmpresa, String mNumPat, String mNumOrdemManutencao){
        this.mNomeEmpresa = mNomeEmpresa;
        this.mNumPat = mNumPat;
        this.mNumOrdemManutencao = mNumOrdemManutencao;
        mPathToPhotos = new ArrayList<String>();
    }

    public boolean addPhoto(String path){
        mPathToPhotos.add(path);
        return true;
    }

    public String getmNomeEmpresa() {
        return mNomeEmpresa;
    }

    public void setmNomeEmpresa(String mNomeEmpresa) {
        this.mNomeEmpresa = mNomeEmpresa;
    }

    public String getmNumPat() {
        return mNumPat;
    }

    public void setmNumPat(String mNumPat) {
        this.mNumPat = mNumPat;
    }

    public String getmNumOrdemManutencao() {
        return mNumOrdemManutencao;
    }

    public void setmNumOrdemManutencao(String mNumOrdemManutencao) {
        this.mNumOrdemManutencao = mNumOrdemManutencao;
    }
}

