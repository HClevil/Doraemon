package com.cofbro.qian.profile

import android.os.Bundle
import android.view.ViewGroup.MarginLayoutParams
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.cofbro.hymvvmutils.base.BaseFragment
import com.cofbro.qian.data.URL
import com.cofbro.qian.databinding.FragmentProfileBinding
import com.cofbro.qian.utils.CacheUtils
import com.cofbro.qian.utils.dp2px
import com.cofbro.qian.utils.getStatusBarHeight
import com.cofbro.qian.view.FullScreenDialog

class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {
    override fun onAllViewCreated(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        adjustMarginOfView()
        profileMessageInfo()
    }

    private fun adjustMarginOfView() {
        val statusBarHeight = getStatusBarHeight(requireContext())
        val layoutParams = binding?.csMyInfo?.layoutParams as? MarginLayoutParams
        layoutParams?.topMargin = statusBarHeight + dp2px(requireContext(), 5)
    }
    private fun profileMessageInfo(){

        viewModel.uid.let {
            val options = RequestOptions().transform(
                CenterCrop(),
                RoundedCorners(dp2px(requireContext(), 5))
            )
            Glide.with(this)
                .load(URL.getAvtarImgPath(it))
                .apply(options)
                .into(binding!!.ivProfileUserIcon)
        }
    }
    private fun viewClick(){
        binding?.tvLogout?.setOnClickListener {
            /**
             * 登出出现dialog
             */
            viewModel.logout_dialog = LogoutDialog(requireContext())
            viewModel.logout_dialog?.setCancelable(false)
            viewModel.logout_dialog?.show()
        }
    }

}