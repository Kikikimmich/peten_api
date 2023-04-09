package com.kimmich.peten.mock;

import java.util.*;

public class UserFollowGenerator {
    final private double alpha;
    final private Random random;

    public UserFollowGenerator(int numUsers, double alpha) {
        this.alpha = alpha;
        this.random = new Random();
    }

    public Map<String, List<String>> generate(List<String> userList) {
        Map<String, List<String>> followMap = new HashMap<>();

        // 初始化关注列表为空
        for (String userId : userList) {
            followMap.put(userId, new ArrayList<>());
        }

        // 按幂律分布生成关注关系
        for (int i = 0; i < userList.size(); i++) {
            int numFollows = getNumFollows();
            List<String> follows = getRandomUsers(numFollows, userList, userList.get(i));
            followMap.get(userList.get(i)).addAll(follows);
        }

        return followMap;
    }

//    public Map<Integer, List<Integer>> generate(int userId, List<Integer> userList) {
//        Map<Integer, List<Integer>> followMap = generate(userList);
//        Map<Integer, List<Integer>> result = new HashMap<>();
//        result.put(userId, followMap.get(userId));
//        return result;
//    }



  /**
   *    参数 alpha 用来控制幂律分布的指数，从而影响生成用户关注数量的分布形态。
   *
   *     幂律分布是一种重尾分布，其概率密度函数的形式为 $p(x) \propto x^{-\alpha}$，其中 $x$ 表示随机变量的取值，$\alpha$ 是分布的指数。在这种分布下，大多数事件的发生频率较低，而少数事件的发生频率较高，呈现出一个长尾的分布形态。在社交网络中，有一些用户具有很多粉丝或关注者，而大多数用户的粉丝或关注者数量相对较少，这种现象就可以用幂律分布来描述。
   *     下面代码中，我使用幂律分布来生成每个用户关注的数量。具体来说，我使用了以下公式来计算关注数量：
   *
   *     $$N = \lfloor (1 - u)^{-1/(\alpha - 1)} \rfloor$$
   *
   *     其中 $u$ 是一个 $[0,1)$ 范围内的随机数，$\alpha$ 是幂律分布的指数，$\lfloor x \rfloor$ 表示 $x$ 的向下取整。这个公式保证了关注数量 $N$ 的期望值为 $1/(\alpha - 1)$，当 $\alpha > 2$ 时，$N$ 的方差也是有限的。
   *     通过控制参数 $\alpha$ 的值，我们可以控制生成的关注数量的分布形态。当 $\alpha$ 的值越小，生成的关注数量呈现出更加长尾的分布形态，即更多的用户关注了很多其他用户，而其他用户的关注数量相对较少。反之，当 $\alpha$ 的值越大，生成的关注数量呈现出更加集中的分布形态，即每个用户关注的其他用户数量相对均衡。
   *
   *
   *     在社交网络中，通常会出现一些用户拥有很多粉丝或关注者，而大多数用户的粉丝或关注者数量相对较少，这种现象可以用幂律分布来描述。在幂律分布中，分布的指数 $\alpha$ 决定了分布的形态，越小的 $\alpha$ 会产生更加长尾的分布形态。
   *     下面是一些 $\alpha$ 的合理值例子：
   *
   *     当 $\alpha = 1.0$ 时，分布的形态最长尾，也就是最不均衡的情况。这种情况下，只有极少数的用户拥有大量的粉丝或关注者，大多数用户的粉丝或关注者数量非常少。
   *     当 $\alpha = 2.0$ 时，分布的形态是 Zipf 分布，这是一种比较常见的分布形态，也是最均衡的情况。在这种情况下，每个用户的粉丝或关注者数量相对均衡。
   *     当 $\alpha > 2.0$ 时，分布的形态逐渐变得更加集中。在这种情况下，大多数用户的粉丝或关注者数量相对较少，只有极少数的用户拥有大量的粉丝或关注者。
   *
   *     在实际应用中，选取合适的 $\alpha$ 值需要根据具体场景来确定。例如，在模拟某个社交网络时，我们可以根据该社交网络的特征来选择 $\alpha$ 值。如果该社交网络的用户关注关系比较不均衡，那么可以选择较小的 $\alpha$ 值；如果该社交网络的用户关注关系比较均衡，那么可以选择较大的 $\alpha$ 值。
   *
   * */

    private int getNumFollows() {
        double u = random.nextDouble();
        return (int) Math.floor(Math.pow(1.0 - u, -1.0 / (alpha - 1.0)));
    }

    private List<String> getRandomUsers(int num, List<String> userList, String exclude) {
        List<String> users = new ArrayList<>(userList);
        users.remove(exclude);
        Collections.shuffle(users);
        return users.subList(0, Math.min(num, users.size()));
    }

}