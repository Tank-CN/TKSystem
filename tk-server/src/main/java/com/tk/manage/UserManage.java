package com.tk.manage;

import com.tk.mapper.UserInfoMapper;
import com.tk.mapper.ex.UserExMapper;
import com.tk.model.User;
import com.tk.model.UserExample;
import com.tk.model.UserInfo;
import com.tk.util.CommonUtils;
import com.tk.util.encryption.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@Transactional(readOnly = true)
public class UserManage extends BaseManage {

    @Autowired
    UserExMapper userExMapper;

    @Autowired
    UserInfoMapper userInfoMapper;


    public int update(User user) {
        return userExMapper.updateByPrimaryKeySelective(user);
    }


    public List<User> list(Integer pageNumber, Integer pageSize) {
        UserExample example = new UserExample();
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return userExMapper.selectByExample(example);
    }

    public int count() {
        return userExMapper.countByExample(null);
    }





    public List<User> search(String nickname, String phone, Integer pageNumber, Integer pageSize) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (!CommonUtils.isNull(nickname) && !CommonUtils.isNull(phone)) {
            criteria.andNicknameLike("%" + nickname + "%");
            criteria.andMobileLike("%" + phone + "%");
        } else if (!CommonUtils.isNull(nickname)) {
            criteria.andNicknameLike("%" + nickname + "%");
        } else if (!CommonUtils.isNull(phone)) {
            criteria.andMobileLike("%" + phone + "%");
        }
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return userExMapper.selectByExample(example);
    }

    public int searchCount(String nickname, String phone) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (!CommonUtils.isNull(nickname) && !CommonUtils.isNull(phone)) {
            criteria.andNicknameLike("%" + nickname + "%");
            criteria.andMobileLike("%" + phone + "%");
        } else if (!CommonUtils.isNull(nickname)) {
            criteria.andNicknameLike("%" + nickname + "%");
        } else if (!CommonUtils.isNull(phone)) {
            criteria.andMobileLike("%" + phone + "%");
        }
        return userExMapper.countByExample(example);
    }

    public User getUserById(Long id) {
        return userExMapper.selectByPrimaryKey(id);
    }





    /**
     * 搜索用户
     *
     * @param key
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<User> search(String key, Integer pageNumber, Integer pageSize) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNicknameLike("%" + key + "%");
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return userExMapper.selectByExample(example);
    }

    /**
     * 创建新账户[api]
     *
     * @param password
     * @return
     */
    public User create(String mobile, String password, String ip) {
        String token = UUID.randomUUID().toString();
        token = token.replace("-", "");
        User user = new User();
        user.setPassword(MD5Utils.getMD5(password));
        user.setToken(token);
        user.setMobile(mobile);
        user.setFlag(Byte.valueOf("1"));
        user.setRegtype(Short.valueOf("1"));
        user.setVip(Byte.valueOf("0"));

        UserInfo userInfo = new UserInfo();
        userInfo.setRegdate(new Date());
        userInfo.setLastlogdate(new Date());
        userInfo.setRegip(ip);
        userInfo.setLastlogip(ip);

        long temp = userExMapper.insertBackId(user);
        if (temp == 1) {
            userInfo.setUid(user.getId());
            if (userInfoMapper.insertSelective(userInfo) > 0) {
                return user;
            }


//			// 注册完成完善信息
//			MsgRecord recordRegister = new MsgRecord();
//			recordRegister.setUid(account.getId());
//			recordRegister.setStatus((byte) 0);// 未查看
//			recordRegister.setEvent((byte) 1);
//			recordRegister.setMsgtype((byte) 3);
//			recordRegister.setDiccode(Constants.MSG_TYPE_0103);
//			recordRegister.setCreatedate(new Date());
//			recordRegister.setContent("您已经注册完成，请到【我的】完善个人信息，方便就医功能的使用!");
//			recordRegister.setComplete((byte) 0);
//			recordRegister.setEventstep((byte) 1);
//			// 上传头像
//			MsgRecord recordHead = new MsgRecord();
//			recordHead.setUid(account.getId());
//			recordHead.setStatus((byte) 0);// 未查看
//			recordHead.setEvent((byte) 0);
//			recordHead.setMsgtype((byte) 2);
//			recordHead.setDiccode(Constants.MSG_TYPE_0102);
//			recordHead.setCreatedate(new Date());
//			recordHead.setContent("请上传个人头像，方便朋友认出你!");
//			recordHead.setComplete((byte) 0);
//			recordHead.setEventstep((byte) 1);
//			// 上传档案
//			MsgRecord recordEhr = new MsgRecord();
//			recordEhr.setUid(account.getId());
//			recordEhr.setStatus((byte) 0);// 未查看
//			recordEhr.setEvent((byte) 0);
//			recordEhr.setMsgtype((byte) 2);
//			recordEhr.setDiccode(Constants.MSG_TYPE_0104);
//			recordEhr.setCreatedate(new Date());
//			recordEhr.setContent("上传病历数据，掌上管理健康数据!");
//			recordEhr.setComplete((byte) 0);
//			recordEhr.setEventstep((byte) 1);
//
//			List<MsgRecord> msgList = new ArrayList<MsgRecord>();
//			msgList.add(recordRegister);
//			msgList.add(recordHead);
//			msgList.add(recordEhr);
//			msgRecordManage.inserBatch(msgList);

        }
        return null;
    }


    /**
     * 根据电话得到Account
     *
     * @param mobile
     * @return
     */
    public User getUserByMobile(String mobile) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(mobile);
        criteria.andFlagEqualTo((byte) 1);
        List<User> accounts = userExMapper.selectByExample(example);
        if (accounts.size() > 0) {
            return accounts.get(0);
        } else {
            return null;
        }
    }

    /**
     * 修改用户信息
     *
     * @return
     */
    public Boolean updateUser(User Account) {
        return userExMapper.updateByPrimaryKeySelective(Account) > 0;
    }


    /**
     * 修改用户真实姓名
     *
     * @param uid  uid
     * @param name 名字
     * @return
     */
    public String modifyName(Long uid, String name) {
        User user = userExMapper.selectByPrimaryKey(uid);
        if (user == null) {
            return "S01";
        }
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setNickname(name);
        int temp = userExMapper.updateByPrimaryKeySelective(newUser);
        if (temp != 1) {
            return "S02";
        }
        return "S200";
    }


    /**
     * 修改用户个性签名
     *
     * @param uid  uid
     * @param info 名字
     * @return
     */
    public String modifyInfo(Long uid, String info) {
        User user = userExMapper.selectByPrimaryKey(uid);
        if (user == null) {
            return "S01";
        }
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setInfo(info);
        int temp = userExMapper.updateByPrimaryKeySelective(newUser);
        if (temp != 1) {
            return "S02";
        }
        return "S200";
    }

    /**
     * 修改生日
     *
     * @param uid      用户id
     * @param birthday 生日
     * @return
     */
    public String modifyBirthday(Long uid, Date birthday) {
        User user = userExMapper.selectByPrimaryKey(uid);
        if (user == null) {
            return "S01";
        }
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setBirthdate(birthday);
        int temp = userExMapper.updateByPrimaryKeySelective(newUser);
        if (temp != 1) {
            return "S02";
        }
        return "S200";
    }


    /**
     * 修改性别
     *
     * @param uid     用户id
     * @param sexcode 性别编码 1男 2
     * @return
     */
    public String modifySex(Long uid, Byte sexcode) {
        User user = userExMapper.selectByPrimaryKey(uid);
        if (user == null) {
            return "S01";
        }
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setSexcode(sexcode);
        int temp = userExMapper.updateByPrimaryKeySelective(newUser);
        if (temp != 1) {
            return "S02";
        }
        return "S200";
    }

    /**
     * 验证用户手机号是否正确
     *
     * @param uid      用户id
     * @param phoneNum 用户手机号
     * @return
     */
    public Boolean verifyPhoneNum(Long uid, String phoneNum) {
        if (phoneNum == null || uid == null) {
            return false;
        }
        User user = userExMapper.selectByPrimaryKey(uid);
        if (user == null) {
            return false;
        }
        if (phoneNum.trim().equals(user.getMobile())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改用户手机号码
     *
     * @param phoneNum  手机号码
     * @param accountId 账户id
     * @return S01 手机号码已经存在 S02账户不存 S03修改失败 S200 修改成功
     */
    public String modifyMobile(String phoneNum, Long accountId) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(phoneNum.trim());
        List<User> list = userExMapper.selectByExample(example);
        if (list.size() > 0) {
            return "S01";
        }

        User account = userExMapper.selectByPrimaryKey(accountId);
        if (account == null) {
            return "S02";
        }
        User accountTemp = new User();
        accountTemp.setId(account.getId());
        accountTemp.setMobile(phoneNum);
        int temp = userExMapper.updateByPrimaryKeySelective(accountTemp);
        if (temp != 1) {
            return "S03";
        }
        return "S200";
    }




}
