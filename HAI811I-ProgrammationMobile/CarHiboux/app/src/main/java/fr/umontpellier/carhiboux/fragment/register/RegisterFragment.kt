package fr.umontpellier.carhiboux.fragment.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import fr.umontpellier.carhiboux.R


class RegisterFragment() : Fragment()
{
    private var particularFragment : ParticularRegisterFragment = ParticularRegisterFragment()
    private var professionalFragment: ProfessionalRegisterFragment = ProfessionalRegisterFragment()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view : View = inflater.inflate(R.layout.register_fragment, container, false)
        setFragment(particularFragment)

        view.findViewById<Switch>(R.id.register_toggle).setOnCheckedChangeListener { _, isChecked ->
            if(isChecked)
            {
                setFragment(professionalFragment)
            }
            else
            {
                setFragment(particularFragment)
            }
        }

        return view
    }

    private fun setFragment(fragment : Fragment)
    {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.register_fragment, fragment)
        transaction.commit()
    }
}