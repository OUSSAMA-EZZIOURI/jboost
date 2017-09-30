package entity;

import db.DBInstance;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

/**
 * SysUser generated by hbm2java
 */
@Entity
@Table(name = "base_user", uniqueConstraints = {
    @UniqueConstraint(columnNames = "login")})
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "id"))
    ,
    @AttributeOverride(name = "create_uid", column = @Column(name = "create_uid"))
    ,
    @AttributeOverride(name = "update_uid", column = @Column(name = "update_uid"))
    ,
    @AttributeOverride(name = "create_time", column = @Column(name = "create_time"))
    ,
    @AttributeOverride(name = "update_time", column = @Column(name = "update_time"))
})
public class BaseUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "login")
    private String login;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "login_time")
    private Date loginTime;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private String gender; // male or female

    @Column(name = "active")
    private int active;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private Set<SysGroup> groups = new HashSet<SysGroup>(0);

    @Transient
    private Set<SysEntityPermission> entityPermissions;

    public Set<SysEntityPermission> getEntityPermissions() {
        return entityPermissions;
    }

    public void setEntityPermissions(Set<SysEntityPermission> entityPermissions) {
        this.entityPermissions = entityPermissions;
    }

    @Transient
    private Set<SysView> viewPermissions;

    public Set<SysView> getViewPermissions() {
        return viewPermissions;
    }

    public void setViewPermissions(Set<SysView> viewPermissions) {
        this.viewPermissions = viewPermissions;
    }

    @Transient
    private Set<SysMenu> menuPermissions;

    public Set<SysMenu> getMenuPermissions() {
        return menuPermissions;
    }

    public void setMenuPermissions(Set<SysMenu> menuPermissions) {
        this.menuPermissions = menuPermissions;
    }

    public BaseUser() {
    }

    public BaseUser(String firstName, String lastName, String login, Date loginTime, String password, int active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.loginTime = loginTime;
        this.password = password;
        this.active = active;
    }

    /**
     * Load the entity permissions list for the given user id
     *
     * @param uid User ID
     * @return Set of access right
     */
    public Set<SysEntityPermission> loadEntityPermissions(int uid) {
        DBInstance.startSession();
        SQLQuery sqlQuery = DBInstance.getSession().createSQLQuery(""
                + "SELECT entity, MAX(c) AS \"C\", MAX(r) AS \"R\", MAX(u) AS \"U\", MAX(d) AS \"D\" "
                + " FROM sys_entity_permission sep\n"
                + " WHERE id IN (\n"
                + "	SELECT perm_id FROM sys_group_entity_perm ga\n"
                + "	WHERE ga.group_id IN (\n"
                + "		SELECT group_id FROM sys_user_group ug\n"
                + "		WHERE ug.user_id = " + uid + "\n"
                + "	)\n"
                + ")  GROUP BY entity;");

        List<Object[]> result = sqlQuery.list();

        DBInstance.getSession().getTransaction().commit();

        Set<SysEntityPermission> permissionsResultSet = new HashSet<SysEntityPermission>(result.size());
        for (Object[] line : result) {
            permissionsResultSet.add(new SysEntityPermission(
                    (String) line[0],
                    Short.valueOf(line[1].toString()),
                    Short.valueOf(line[2].toString()),
                    Short.valueOf(line[3].toString()),
                    Short.valueOf(line[4].toString())));
        }

        return permissionsResultSet;
    }

    /**
     * Check if the login and password is correct
     *
     * @param login Login of the user
     * @param password Password of the user
     * @return The user object if the authentication succeed, null otherwise
     */
    public BaseUser authenticate(String login, String password) {
        DBInstance.startSession();
        DBInstance.getSession().beginTransaction();
        Query query = DBInstance.getSession().createQuery(
                "FROM " + this.getClass().getSimpleName()
                + " WHERE "
                + " login = :login AND "
                + " password = :password");
        query.setParameter("login", login)
                .setParameter("password", password)
                .setFirstResult(0)
                .setMaxResults(1);
        DBInstance.getSession().getTransaction().commit();
        List result = query.list();
        if (result.isEmpty()) {
            return null;
        }
        return (BaseUser) result.get(0);
    }

    public Set<SysGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<SysGroup> groups) {
        this.groups = groups;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "BaseUser{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", login=" + login + ", loginTime=" + loginTime + ", password=" + password + ", email=" + email + ", phone=" + phone + ", gender=" + gender + ", active=" + active + ", groups=" + groups + '}';
    }

    
}
