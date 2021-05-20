package com.b21cap0237.codelabjetpack.reader.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.b21cap0237.codelabjetpack.R
import com.b21cap0237.codelabjetpack.data.ContentEntity
import com.b21cap0237.codelabjetpack.data.ModuleEntity
import com.b21cap0237.codelabjetpack.databinding.FragmentModuleContentBinding
import com.b21cap0237.codelabjetpack.reader.CourseReaderViewModel
import com.b21cap0237.codelabjetpack.viewmodel.ViewModelFactory


class ModuleContentFragment : Fragment() {
    companion object {
        val TAG: String = ModuleContentFragment::class.java.simpleName
        fun newInstance(): ModuleContentFragment = ModuleContentFragment()
    }

    private lateinit var fragmentModuleContentBinding: FragmentModuleContentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentModuleContentBinding = FragmentModuleContentBinding.inflate(inflater, container, false)
        return fragmentModuleContentBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
//            val content = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">Contoh Content</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
//            val viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())[CourseReaderViewModel::class.java]
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]
//            val module = viewModel.getSelectedModule()
            fragmentModuleContentBinding.progressBar.visibility = View.VISIBLE
            viewModel.getSelectedModule().observe(viewLifecycleOwner, { module ->
                fragmentModuleContentBinding.progressBar.visibility = View.GONE
                if (module != null) {
                    populateWebView(module)
                }
            })
//            populateWebView(content)
//            populateWebView(module)
        }
    }
    private fun populateWebView(module: ModuleEntity) {
//        fragmentModuleContentBinding.webView.loadData(content.content ?: "", "text/html", "UTF-8")
        fragmentModuleContentBinding.webView.loadData(module.contentEntity?.content ?: "", "text/html", "UTF-8")
    }
}