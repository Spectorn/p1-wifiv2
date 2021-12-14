package com.example.wifiv3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    long Download;
    long Upload;
    String BSSID;
    long RSSI;
    TextView Info;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_results, container, false);
        TextView Info = (TextView) view.findViewById(R.id.InfoText);
        ImageView Check = (ImageView) view.findViewById(R.id.Check);

        return view;

    }




    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    public void BasisResult(){
        System.out.println("Basis værdier er sat");
        activity_wifitest activity = (activity_wifitest) getActivity();
        if (activity != null) {
            activity.SetLayout();
        }
        else{
            System.out.println("FEJL I RESULT");
        }
    }

    public void FirstTest(){
        int DownloadScore;
        WifiInformation TestOne = new WifiInformation(Download, Upload, RSSI, BasisDownload, BasisUpload, BasisRSSI);
        DownloadScore = TestOne.CheckDownload();





    }



    public void ClearLayout(){

        System.out.println("layout clear");
    }


    // Dette er funktionen hvori alt information bliver delt mellem activities og de 2 fragments
    @Override
    public void WifiData(int TestType,long Download, long Upload, String BSSID, long RSSI) {
        System.out.println("Test typen er: "+TestType+"Download er: "+Download+"Upload er: "+Upload+"BSSID er: "+BSSID+"RSSI er: "+RSSI + "Hilsen fragment to");
        switch(TestType){
            case 1:
                TestTypeResult = TestType; Download = BasisDownload; Upload = BasisUpload; BSSID = BasisBSSID; RSSI = BasisRSSI;
                BasisResult();
            case 2:
                TestTypeResult = TestType; Download = Download; Upload = Upload; BSSID = BSSID; RSSI = RSSI;
                FirstTest();


        }


    }
}


