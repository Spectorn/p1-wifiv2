package com.example.wifiv3;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class fragment_result extends Fragment implements DataSender{

    public fragment_result(){
    }


    long BasisDownload;
    long BasisUpload;
    String BasisBSSID;
    long BasisRSSI;

    int TestTypeResult;
    long R_Download;
    long R_Upload;
    String R_BSSID;
    long R_RSSI;
    TextView Info;
    Button button;
    Button buttonOne;
    ImageView Check;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_results, container, false);
        TextView Info = (TextView) view.findViewById(R.id.InfoText);
        Check = (ImageView) view.findViewById(R.id.Check);
        button = (Button) view.findViewById(R.id.button);
        buttonOne = (Button) view.findViewById(R.id.button2);

        return view;
    }

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



    }

    public void BasisResult(){
        System.out.println("Basis værdier er sat");
        activity_wifitest activity = (activity_wifitest) getActivity();
            activity.SetLayout();

    }

    public void FirstTest(){
        int DownloadRank;
        int UploadRank;
        int Ranking;
        WifiInformation TestOne = new WifiInformation(R_Download, R_Upload, R_RSSI, BasisDownload, BasisUpload, BasisRSSI);
        DownloadRank = TestOne.CheckDownload();
        UploadRank = TestOne.CheckUpload();
        Ranking = TestOne.FinalRank(DownloadRank, UploadRank);
        System.out.println(Ranking);
        switch(Ranking){
            case 1:
                GoodResult();
                System.out.println("Kører GoodResult");
                break;
        }
        activity_wifitest activity = (activity_wifitest) getActivity();
        activity.SetLayout();



    }



    public void ClearLayout(){
        System.out.println("layout clear");
    }

    public void GoodResult(){
        Check.setVisibility(View.GONE);
        button.setVisibility(View.VISIBLE);
        buttonOne.setVisibility(View.VISIBLE);
    }



    // onclick for detaljer button
    public void GetDetails(View view){

    }

    // Dette er funktionen hvori alt information bliver delt mellem activities og de 2 fragments
    @Override
    public void WifiData(int TestType,long Download, long Upload, String BSSID, long RSSI) {
        System.out.println("Test typen er: "+TestType+"Download er: "+Download+"Upload er: "+Upload+"BSSID er: "+BSSID+"RSSI er: "+RSSI + "Sendt fra fragment result");
        switch(TestType){
            case 1:
                TestTypeResult = TestType; Download = BasisDownload; Upload = BasisUpload; BSSID = BasisBSSID; RSSI = BasisRSSI;
                BasisResult();
                break;
            case 2:
                TestTypeResult = TestType; R_Download = Download; R_Upload = Upload; R_BSSID = BSSID; R_RSSI = RSSI;
                FirstTest();
                break;
        }
    }
}


