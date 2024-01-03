#include <bits/stdc++.h>
using namespace std;
typedef long long int ll;
const unsigned int mod = 1e9+7;

auto _ = [](){
	ios_base::sync_with_stdio(0);
	freopen("err.txt","w",stderr);
	cin.tie(NULL);
	cout.tie(NULL);
	return 0;
}();


void dbg(map<int, map<int, int>> &mp) {

	for(auto a : mp) {
		cerr << "[ " << a.first << " ( ";
		for(auto it : a.second) {
			cerr << it.first << "=" << it.second << " ";
		}
		cerr << " ) ]" << endl;
	}
}


void dbg2(map<int, int> &par) {
	for(auto &a : par) {
		cerr << "(" << a.first << "->" << a.second << ")" << endl; 
	}
}


int prime[100000000];

void sieve(int N) {

	memset(prime, 1, sizeof(prime));
	for(int p=2; p*p <= N; ++p) {

		if(prime[p]) {
			for(int i=p*p; i<=N; !prime[i], i+=p);
		}
	}

}

vector<long long> palins;

void genPalin(string s, int len) {
	if(s.length() == ceil((double)len/(double)2)) {
		if(len&1) {
			for(int i=s.length()-2; i>=0; --i) s += s[i];
			palins.push_back(stoll(s));
		} else {
			for(int i=s.length()-1; i>=0; --i) s += s[i];
			palins.push_back(stoll(s));
		}
		return;
	}

	if(s.empty()) {
		for(int i=1; i<=9; ++i) {
		s += (char)('0' + i);
		genPalin(s, len);
		s.pop_back();
		}
	} else {
		for(int i=0; i<=9; ++i) {
		s += (char)('0' + i);
		genPalin(s, len);
		s.pop_back();
		}
	}
	
}

int32_t main() {

	string word = "aeiaaioaaaaeiiiiouuuooaauuaeiu";
	vector<char> vow = {'a','e','i','o','u'};

        int ans = 0,l=0,r=0,p=0;
        for(; r<word.length(); ++r) {
            if(word[r] == vow[p]) {
                if(p == 4) ans = max(ans, r-l+1);
                cout << "#";
            } else if(p < 4 and word[r] == vow[p+1]) {
                ++p;
                if(p == 4) ans = max(ans, r-l+1);
                cout << "$";
            } else {
                while(l<word.length() and word[l] != 'a') {
                	++l;
                }
                p=1;
                r = l;
                cout << "%";
            }

        }

        if(p == 4) ans = max(ans, r-l+1);
        cout<< ans;

	// genPalin("", 0);
	// genPalin("", 2);
	// genPalin("", 3);
	// genPalin("", 4);
	// genPalin("", 5);
	// genPalin("", 6);
	// genPalin("", 7);
	// genPalin("", 8);
	// genPalin("", 9);
	
	// vector<int> nums {1};
	// sort(begin(nums), end(nums));
	// int n = nums.size(), m;
	// if(n&1) {
	// 	m = nums[n/2];
	// } else {
	// 	m = (nums[(n/2)] + nums[(n/2)-1])/2;
	// }


	// auto ind = lower_bound(begin(palins), end(palins), m) - begin(palins);

	// long long cost = LLONG_MAX;
	// long long sum = 0;

	// if(ind < palins.size()) {
	// 	for(auto &a : nums) sum += abs(a-palins[ind]);
	// 	cost = min(cost, sum);
	// 	sum = 0;
	// 	cout << "#1 " << cost << endl;
	// }
	// if(ind > 0) {
	// 	for(auto &a : nums) sum += abs(a-palins[ind-1]);
	// 	cost = min(cost, sum);
	// 	sum = 0;
	// 	cout << "#2 " << cost << endl;
	// }
	// if(ind < palins.size()-1) {
	// 	for(auto &a : nums) sum += abs(a-palins[ind+1]);
	// 	cost = min(cost, sum);
	// 	sum = 0;	
	// 	cout << "#3 " << cost << endl;
	// }
	

	// cout << cost;


	// for(auto &p : palins) { cout << p << endl; }
	// generate 9 - length palindrome number


	
	
	// vector<int> nums {6,3,0,1};

	// int n = nums.size();
	// if(n&1) return 0;

	// sort(begin(nums), end(nums));

	// unordered_map<int,int> mp;
	// vector<int> res;
	// for(int i=0; i<nums.size(); ++i) {
	// 	if(mp.find(nums[i]) == mp.end()) {
	// 		res.push_back(nums[i]);
	// 		mp[2*nums[i]]++;
	// 	} else {
	// 		if(--mp[nums[i]] == 0) mp.erase(nums[i]);
	// 	}

	// }

	// if(mp.empty()) {

	// 	for(auto &a : res) cout << a<< " ";
	// } else cout << "X";

	
	// vector<int> nums {1,4,10};
	// int target = 19;

	// sort(begin(nums), end(nums));

	// ll sum = 0, cnt = 0;
	// for(int i=0; i<nums.size();) {
	// 	if(sum >= target) break;
	// 	if(nums[i] > (sum+1)) {
	// 		++cnt;
	// 		sum = 2*sum + 1;
	// 	} else {
	// 		while(i < nums.size() and nums[i] <= (sum+1)) sum += nums[i++];
	// 	}
	// }	
	
	// while(sum < target) {
	// 	++cnt;
	// 	sum = 2*sum+1;
	// }

	// cout << cnt << endl;
	
	


	// int arr[3] = {3,4,1};
	// int N = 3, L = 2, R = 4;
	
	// // solve for atmost R
	// int l=0,r=0, cnt1=0, cnt0=0;
	// for(; r<N; ++r) {
	// 	if(arr[r] > R) {
	// 		int n = r-l;
	// 		cnt1 += (n*(n+1))/2;
	// 		l = r+1;
	// 	}

	// }

	// if(r > l) {
	// 	int n = r-l;
	// 	cnt1 += (n*(n+1))/2;
	// }
	
	// // count for atmost L-1
	// r=0,l=0;
	// while(r < N) {
	// 	if(arr[r] >= L) {
	// 		int n = r-l;
	// 		cnt0 += (n*(n+1))/2;
	// 		l=r+1;
	// 	}
	// 	++r;
	// }
	
	// if(r > l) {
	// 	int n = r-l;
	// 	cnt0 += (n*(n+1))/2;
	// }

	// cout << (cnt1-cnt0) << endl;


	// int c =3;

	// ll x = floor((double)sqrt(c));
	// for(ll i=1; i<=x; ++i) {
	// 	ll r = c - (i*i);
	// 	ll l = sqrt(r);
	// 	if(l*l == r) {
	// 		cout << 1; break;
	// 	}
	// }

	// cout << (int)(pow(2,31)-1);
	


	
	// vector<int> nums {1,7,6,18,2,1};	
	// int limit=3;

	// vector<pair<int, int>> vp;
	// for(int i=0; i<nums.size(); ++i) {
	// 	vp.push_back({nums[i], i});
	// }

	// std::sort(begin(vp), end(vp), [](auto &p1, auto &p2) {
	// 	return p1.first < p2.first;
	// });

	// for(int i=0; i<nums.size(); ++i) {
	// 	vector<int> ti, tn;
	// 	if(i == 0) {
	// 		ti.push_back(vp[i].second);
	// 		tn.push_back(vp[i].first);
	// 		continue;
	// 	}

	// 	while(i < vp.size() and vp[i].first - vp[i-1].first <= limit) {

	// 		ti.push_back(vp[i].second);
	// 		tn.push_back(vp[i].first);
	// 		++i;
	// 	}

	// 	sort(begin(ti), end(ti));
	// 	sort(begin(tn), end(tn));

	// 	for(int j=0; j<ti.size(); ++j) {
	// 		nums[ti[j]] = tn[j];
	// 	}
	// }

	// for(auto &i : nums) cout << i << " ";


	// map<int, int> par, cnt;
	// map<int, map<int, int>> comp;

	// for(int i=0; i<nums.size(); ++i) {
	// 	par[nums[i]] = nums[i];
	// 	comp[nums[i]][nums[i]]++;
	// 	cnt[nums[i]]++;
	// }

	// dbg(comp);


	// vector<int> nums2(begin(nums), end(nums));
	// sort(begin(nums2), end(nums2));
	
	// for(auto it = ++begin(cnt); it != end(cnt); ++it) {
	// 	int c = it->first, p = (prev(it))->first;
	// 	if(c-p <= limit) {
	// 		par[c] = par[p];
	// 		comp[par[c]][c] = cnt[c];
	// 	}
	// }


	// dbg(comp);
	// cerr << endl;
	// dbg2(par);

	// for(int i=0; i<nums.size(); ++i) {
	// 	int p = par[nums[i]];
		

	// 	int t = begin(comp[p])->first;
	// 	nums[i] = t;
	// 	if(--comp[p][t] == 0) comp[p].erase(t);
	// }

	// for(int i=0; i<nums.size(); ++i) cout << nums[i] << " ";



	exit(0);
}
