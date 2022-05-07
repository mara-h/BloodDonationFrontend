package cg.example.blooddonationfrontend.view;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import cg.example.blooddonationfrontend.R;


public class InfoFragment extends Fragment {
    int currentPos = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment, container, false);
        ImageView iv1 = view.findViewById(R.id.ivBefore);
        Button btn1 = view.findViewById(R.id.btn1);
        TextView title = view.findViewById(R.id.titleInput);
        TextView text = view.findViewById(R.id.textInput);

        switch (currentPos) {
            case 0:
                iv1.setImageResource(R.drawable.donorinfo);
                title.setText("De ce să donezi sânge");
                text.setText("√ Sângele donar va fi întotdeauna necesar și  poate ajuta la salvarea unei vieți.\n" + "√ Procedura de donare este una simplă, sigură și inofensivă.\n"+ "√ Este un mod de a te implica activ în nevoile comunități, de a susține sănătatea comunității.\n");
                btn1.setText(">");
                break;
            case 1:
                iv1.setImageResource(R.drawable.donorinfo);
                btn1.setText(">");
                title.setText("Sfaturi înainte de donare");
                text.setText("În dimineața dinaintea donării asigură-te că te-ai odihnit corespunzător, că ești bine hidratat (consumând cel puțin 500 ml de apă înainte să te prezinți la centrul de transfuzie) și că ai luat un mic dejun sănătos. Evită pe cât posibil alimentele grase și fumatul. Poți bea liniștit cafea (fără lapte) sau ceai. În cazul în care nivelul glicemiei tale este scăzut, fapt ce îl vei afla în urma controlului preliminar, vei putea consuma un baton de ciocolată.");
                break;
            case 2:
                iv1.setImageResource(R.drawable.appointmentimg);
                btn1.setText(">");
                title.setText("Cât sânge donezi");
                text.setText("Se recoltează sânge persoanelor sănătoase, într-o stare fizică și psihică bună, cu vârsta cuprinsă între 18-60 ani, la un interval de minimum două-trei luni între donări, fără a depăși 4-5 donări de sânge pe an în cazul bărbaților și 3-4 donări pe an în cazul femeilor. La o donare de sânge se recoltează 450 ml de sânge. Ca și volum, această cantitate de sânge se reface în câteva ore, iar din punct de vedere celular, în două săptămâni. Recoltarea sângelui durează aproximativ 8-10 minute după care se recomandă să mai rămâi întins pe pat timp de încă 5-10 minute pentru ca personalul medical să poată observa cum a răspuns organismul tău la donare.");
                break;
            case 3:
                iv1.setImageResource(R.drawable.p1);
                btn1.setText(">");
                title.setText("Sfaturi după donare");
                text.setText("După donare se recomandă să consumi multe lichide necarbogazoase și să eviți cosnumul de alcool, fumatul, dar și  efortul fizic intens timp de 12 ore, corpul tău având nevoie de puțin timp pentru a se acomoda cu schimbările produse în timpul transfuziei." );
                break;
            case 4:
                iv1.setImageResource(R.drawable.p3);
                btn1.setText(">");
                title.setText("Beneficiile donării");
                text.setText("√ După donare, sângele este împrospătat, imunitaea organsimului cește, iar riscul de a face accident vascular cerebral, infarct miocardic sau cancer se reduce cu până la 30%.\n" + "√ La fiecare donare, sângele recoltat este supus unor teste biologice precum: determinarea grupei sanguine și a RH-ului, determinarea hemoglobinei și a glicemiei, depsitarea sifilisului, a hepatitei virale B și C, a enzimelor hepatice și depsitarea anticorpilor antiHIV și antiHTLV.\n" + "√ Creșterea rezistenței la șoc hemoragic în cazul pierderii de sânge suferite în urma unui accident.\n" + "√ În ziua donării primești o adeverință pentru 1 zi liberă de la locul de muncă. În plus mai primești 7 tichete de masă.\n");
                break;
            case 5:
                iv1.setImageResource(R.drawable.p3);
                title.setText("Siguranța donării");
                text.setText("Nu există niciun risc pentru donatori în timpul procesului de donare. Trusa utilizată pentru recoltarea sângelui este sterilă și de unică folosință. Pentru primitori, riscurile sunt eliminate pentru că fiecare cantitate de sânge recoltată este testată, iar dacă nu este conform, sângele se incinerează.");
                //btn1.setText("ÎNAPOI LA MENIU");

                break;
        }
        return view;

    }

    public void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
    }
}
